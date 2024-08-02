package teamchallenge.server.catalog.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.author.service.AuthorService;
import teamchallenge.server.catalog.book.exception.BookNotFoundException;
import teamchallenge.server.catalog.book.entity.BookRepository;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.BookSpecification;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;
import teamchallenge.server.catalog.book.entity.Book;
import teamchallenge.server.catalog.category.service.CategoryService;
import teamchallenge.server.catalog.image.service.ImageService;
import teamchallenge.server.catalog.language.entity.Language;
import teamchallenge.server.catalog.language.service.LanguageService;

import java.time.LocalDateTime;
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
    private final ImageService imageService;

    @Override
    public ResponseBookDto createBook(CreateBookDto createBookDto) {
        Book book = new Book();
        book.setTitle(createBookDto.getTitle());
        book.setCategories(categoryService.getCategories(createBookDto.getCategories()));
        book.setAuthors(authorService.getAuthors(createBookDto.getAuthors()));
        book.setDescription(createBookDto.getDescription());
        book.setYear(createBookDto.getYear());
        book.setPrice(createBookDto.getPrice());
        book.setTotalQuantity(createBookDto.getTotalQuantity());
        book.setExpected(createBookDto.isExpected());
        book.setLanguages(languageService.getLanguages(createBookDto.getLanguages()));
        book.setDiscount(createBookDto.getDiscount());
        book.setCreatedAt(LocalDateTime.now());
        return mapBookToResponseBookDto(bookRepository.save(book));
    }

    @Override
    public void saveImages(Long id, MultipartFile image) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setImages(imageService.saveImage(image));
        bookRepository.save(book);
    }

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
                .image(imageService.getImageDto(book.getImages()))
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
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }
}
