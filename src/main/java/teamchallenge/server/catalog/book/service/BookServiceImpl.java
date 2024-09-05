package teamchallenge.server.catalog.book.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.author.entity.AuthorRepository;
import teamchallenge.server.catalog.author.service.AuthorService;
import teamchallenge.server.catalog.book.exception.BookNotFoundException;
import teamchallenge.server.catalog.book.entity.BookRepository;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.BookSpecification;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;
import teamchallenge.server.catalog.book.entity.Book;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.category.entity.CategoryRepository;
import teamchallenge.server.catalog.category.service.CategoryService;
import teamchallenge.server.catalog.language.entity.Language;
import teamchallenge.server.catalog.language.entity.LanguageRepository;
import teamchallenge.server.catalog.language.service.LanguageService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final LanguageService languageService;
    private final AmazonS3 amazonS3;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;

//    @Override
//    public ResponseBookDto createBook(CreateBookDto createBookDto) {
//        Book book = new Book();
//        book.setTitle(createBookDto.getTitle());
//        book.setCategories(categoryService.getCategories(createBookDto.getCategories()));
//        book.setAuthors(authorService.getAuthors(createBookDto.getAuthors()));
//        book.setDescription(createBookDto.getDescription());
//        book.setYear(createBookDto.getYear());
//        book.setPrice(createBookDto.getPrice());
//        book.setTotalQuantity(createBookDto.getTotalQuantity());
//        book.setExpected(createBookDto.isExpected());
//        book.setLanguages(languageService.getLanguages(createBookDto.getLanguages()));
//        book.setDiscount(createBookDto.getDiscount());
//        book.setCreatedAt(LocalDateTime.now());
//        return mapBookToResponseBookDto(bookRepository.save(book));
//    }

//    @Override
//    public void saveImages(Long id, MultipartFile image) {
//        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//        book.setImages(imageService.saveImage(image));
//        bookRepository.save(book);
//    }

    @Override
    @Transactional
    public ResponseBookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::mapBookToResponseBookDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

//    @Override
//    @Transactional
//    public Page<ListResponseBookDto> getBooks(Pageable pageable, Long category) {
//        if (category != null) {
//            return bookRepository.findAllByCategories(categoryService.getCategoryById(category), pageable)
//                    .map(this::mapBookToListResponseBookDto);
//        }
//        return bookRepository.findAll(pageable)
//                .map(this::mapBookToListResponseBookDto);
//    }

//    @Override
//    @Transactional
//    public Page<ListResponseBookDto> getBooks(Pageable pageable, String category) {
//        if (category != null) {
//            return bookRepository.findAllByCategories(categoryService.getCategoryByName(category), pageable)
//                    .map(this::mapBookToListResponseBookDto);
//        }
//        return bookRepository.findAll(pageable)
//                .map(this::mapBookToListResponseBookDto);
//    }

    @Override
//    @Transactional
    public Page<ListResponseBookDto> getBooks(
            Pageable pageable,
            String stringCategory,
            String stringPrice,
            String stringYear,
            String stringLanguage,
            String stringAuthor,
            String stringExpected,
            String stringTitle) {

        Specification<Book> spec = Specification.where(null);

        if (stringCategory != null && !stringCategory.isEmpty()) {
            List<String> category = Arrays.asList(stringCategory.split(","));
            spec = spec.and(BookSpecification.hasCategoryIn(category));
        }
        if (stringAuthor != null && !stringAuthor.isEmpty()) {
            List<String> author = Arrays.asList(stringAuthor.split(","));
            spec = spec.and(BookSpecification.hasAuthorIn(author));
        }
        if (stringLanguage != null && !stringLanguage.isEmpty()) {
            List<String> language = Arrays.asList(stringLanguage.split(","));
            spec = spec.and(BookSpecification.hasLanguageIn(language));
        }
        if (stringPrice != null && !stringPrice.isEmpty()) {
            String[] price = stringPrice.split(",");
            spec = spec.and(BookSpecification.hasPriceBetween(Double.valueOf(price[0]), Double.valueOf(price[1])));
        }
        if (stringYear != null && !stringYear.isEmpty()) {
            List<Long> year = Arrays.stream(stringYear.split(","))
                    .map(Long::valueOf)
                    .toList();
            spec = spec.and(BookSpecification.hasYear(year));
            //spec = spec.and(BookSpecification.hasYearBetween(Long.valueOf(year[0]), Long.valueOf(year[1])));
        }
        if (stringExpected != null && !stringExpected.isEmpty()) {
            Boolean expected = Boolean.valueOf(stringExpected);
            spec = spec.and(BookSpecification.isExpected(expected));
        }
        if (stringTitle != null && !stringTitle.isEmpty()) {
            spec = spec.and(BookSpecification.hasTitleContaining(stringTitle));
        }

        return bookRepository.findAll(spec, pageable)
                .map(this::mapBookToListResponseBookDto);
    }

    @Override
    public List<ResponseBookDto> searchBooks(BookSearchCriteria criteria) {
        Specification<Book> spec = Specification.where(BookSpecification.searchByAuthor(criteria.getAuthor())
                .and(BookSpecification.searchByTitle(criteria.getTitle()))
                .and(BookSpecification.searchByLanguage(criteria.getLanguage())
                        .and(BookSpecification.searchByYear(criteria.getYear()))
                        .and(BookSpecification.searchByPrice(criteria.getPrice())
                                .and(BookSpecification.searchByCategory(criteria.getCategory())
                                )))
        );
        return bookRepository.findAll(spec).stream()
                .map(this::mapBookToResponseBookDto)
                .toList();
    }

    private ListResponseBookDto mapBookToListResponseBookDto(Book book) {
        return ListResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .languages(book.getLanguages()
                        .stream()
                        .map(Language::getName)
                        .toList())
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .toList())
                .imageUrl(amazonS3.getUrl(bucketName, book.getImageKey()).toString())
                .build();
    }

    public ResponseBookDto mapBookToResponseBookDto(Book book) {
        return ResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categories(book.getCategories())
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .toList())
                .description(book.getDescription())
                .year(book.getYear())
                .price(book.getPrice())
                .languages(book.getLanguages()
                        .stream()
                        .map(Language::getName)
                        .toList())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .imageUrl(amazonS3.getUrl(bucketName, book.getImageKey()).toString())
                .build();
    }

    public void addBook(MultipartFile photo, String title, List<String> categoryNames, List<String> authorNames,
                        String description, int year, List<String> languageNames, double price,
                        int totalQuantity, boolean isExpected, Integer discount) throws IOException {

        // Сохранение фото на S3
        String imageKey = null;
        if (photo != null && !photo.isEmpty()) {
            imageKey = saveImageToS3(photo);
        }
        // Получение категорий, авторов и языков по их названиям
        List<Category> categories = (categoryNames != null) ? getCategoriesByName(categoryNames) : new ArrayList<>();
        List<Author> authors = (authorNames != null) ? getAuthorsByName(authorNames) : new ArrayList<>();
        List<Language> languages = (languageNames != null) ? getLanguagesByName(languageNames) : new ArrayList<>();

        // Создание и сохранение книги
        Book book = new Book();
        book.setTitle(title);
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setDescription(description);
        book.setYear(year);
        book.setLanguages(languages);
        book.setImageKey(imageKey);
        book.setPrice(price);
        book.setTotalQuantity(totalQuantity);
        book.setExpected(isExpected);
        book.setDiscount(discount);

        bookRepository.save(book);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    private String saveImageToS3(MultipartFile photo) throws IOException {
        String fileName = "images/" + System.currentTimeMillis() + "_" + photo.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/webp");
        metadata.setContentLength(photo.getSize());

        amazonS3.putObject(bucketName, fileName, photo.getInputStream(), metadata);
        return fileName;
    }

    private List<Category> getCategoriesByName(List<String> categoryNames) {
        List<Category> categories = new ArrayList<>();
        for (String name : categoryNames) {
            Category category = categoryRepository.findByName(name).orElseThrow(() -> new RuntimeException("Category not found"));
            if (category != null) {
                categories.add(category);
            }
        }
        return categories;
    }

    private List<Author> getAuthorsByName(List<String> authorNames) {
        List<Author> authors = new ArrayList<>();
        for (String name : authorNames) {
            Author author = authorRepository.findByName(name).orElseThrow(() -> new RuntimeException("Author not found"));
            if (author != null) {
                authors.add(author);
            }
        }
        return authors;
    }

    private List<Language> getLanguagesByName(List<String> languageNames) {
        List<Language> languages = new ArrayList<>();
        for (String name : languageNames) {
            Language language = languageRepository.findByName(name).orElseThrow(() -> new RuntimeException("Language not found"));
            if (language != null) {
                languages.add(language);
            }
        }
        return languages;
    }
}
