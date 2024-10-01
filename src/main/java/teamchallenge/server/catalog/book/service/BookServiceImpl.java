package teamchallenge.server.catalog.book.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
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
import teamchallenge.server.catalog.image.service.ImageService;
import teamchallenge.server.catalog.language.entity.Language;
import teamchallenge.server.catalog.language.entity.LanguageRepository;
import teamchallenge.server.catalog.language.service.LanguageService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final LanguageService languageService;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final ImageService imageService;


    @Override
    @Transactional
    public ResponseBookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::mapBookToResponseBookDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


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


    public Long addBook(MultipartFile photo, CreateBookDto createBook) throws IOException {
        // Сохранение фото на S3
        String imageKey = null;
        Book result;
        if (photo != null && !photo.isEmpty()) {
            imageKey = imageService.saveImageToS3(photo);
        }
        // Получение категорий, авторов и языков по их названиям
        List<Category> categories = (createBook.getCategoryNames() != null) ? categoryService.getCategoriesByName(createBook.getCategoryNames()) : new ArrayList<>();
        List<Author> authors = (createBook.getAuthorNames() != null) ? authorService.getAuthorsByName(createBook.getAuthorNames()) : new ArrayList<>();
        List<Language> languages = (createBook.getLanguageNames() != null) ? languageService.getLanguagesByName(createBook.getLanguageNames()) : new ArrayList<>();

        // Создание и сохранение книги
        Book book = new Book();
        book.setTitle(createBook.getTitle());
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setDescription(createBook.getDescription());
        book.setYear(createBook.getYear());
        book.setLanguages(languages);
        book.setImageKey(imageKey);
        book.setPrice(createBook.getPrice());
        book.setTotalQuantity(createBook.getTotalQuantity());
        book.setExpected(createBook.isExpected());
        book.setDiscount(createBook.getDiscount());

        result = bookRepository.save(book);
        return result.getId();
    }

    public Long changeImage(MultipartFile photo, Long id) throws IOException {
        Book book = bookRepository.getById(id);
        String oldImageKey = book.getImageKey();
        String newImageKey = null;
        if (photo != null && !photo.isEmpty()) {
            newImageKey = imageService.saveImageToS3(photo);
            if (oldImageKey != null) {
                imageService.deleteImageFromS3(oldImageKey);
            }
        } else if (photo == null || photo.isEmpty()) {
            if (oldImageKey != null) {
                imageService.deleteImageFromS3(oldImageKey);
            }
            newImageKey = null;
        }
        book.setImageKey(newImageKey);
        Book result = bookRepository.save(book);
        return result.getId();
    }

    public void deleteBookById(Long id){
        Book book = bookRepository.getById(id);
        String oldImageKey = book.getImageKey();
        if (oldImageKey != null) {
            imageService.deleteImageFromS3(oldImageKey);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Integer> getAllYears(){
        return bookRepository.findAllYears();
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
                .imageUrl(imageService.getImageUrl(book.getImageKey()))
                .discount(book.getDiscount())
                .discountPrice(book.getPrice()-((book.getPrice()*book.getDiscount())/100))
                .build();
    }

    public ResponseBookDto mapBookToResponseBookDto(Book book) {
        return ResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categories(book.getCategories())
                .authors(book.getAuthors())
                .description(book.getDescription())
                .year(book.getYear())
                .price(book.getPrice())
                .languages(book.getLanguages())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .imageUrl(imageService.getImageUrl(book.getImageKey()))
                .discount(book.getDiscount())
                .discountPrice(book.getPrice()-((book.getPrice()*book.getDiscount())/100))
                .build();
    }
}
