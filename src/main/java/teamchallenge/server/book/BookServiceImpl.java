package teamchallenge.server.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.image.ImageService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;
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
        book.setLanguage(createBookDto.getLanguage());
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

    @Override
    @Transactional
    public Page<ListResponseBookDto> getBooks(Pageable pageable, Long category) {
        if (category != null) {
            return bookRepository.findAllByCategories(pageable, categoryService.getCategoryById(category))
                    .map(this::mapBookToListResponseBookDto);
        }
        return bookRepository.findAll(pageable)
                .map(this::mapBookToListResponseBookDto);
    }

    private ListResponseBookDto mapBookToListResponseBookDto(Book book) {
        return ListResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year(book.getYear())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .language(book.getLanguage())
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
                .language(book.getLanguage())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }
}
