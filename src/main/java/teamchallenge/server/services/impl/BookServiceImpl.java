package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.dto.ListResponseBookDto;
import teamchallenge.server.dto.ResponseBookDto;
import teamchallenge.server.entities.Author;
import teamchallenge.server.entities.Book;
import teamchallenge.server.entities.Category;
import teamchallenge.server.exception.BookNotFoundException;
import teamchallenge.server.repositories.BookRepository;
import teamchallenge.server.services.AuthorService;
import teamchallenge.server.services.BookService;
import teamchallenge.server.services.CategoryService;
import teamchallenge.server.services.ImageService;
import teamchallenge.server.utils.ImageUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<ListResponseBookDto> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::mapBookToListResponseBookDto);
    }

    private ListResponseBookDto mapBookToListResponseBookDto(Book book) {
        return ListResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year(book.getYear())
                .price(book.getPrice())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .collect(Collectors.toList()))
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }

    public ResponseBookDto mapBookToResponseBookDto(Book book) {
        return ResponseBookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .categories(book.getCategories()
                        .stream()
                        .map(Category::getName)
                        .collect(Collectors.toList()))
                .authors(book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .collect(Collectors.toList()))
                .description(book.getDescription())
                .year(book.getYear())
                .price(book.getPrice())
                .totalQuantity(book.getTotalQuantity())
                .isExpected(book.isExpected())
                .image(imageService.getImageDto(book.getImages()))
                .build();
    }
}
