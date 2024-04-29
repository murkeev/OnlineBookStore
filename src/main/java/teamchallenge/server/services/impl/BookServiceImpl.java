package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.entities.Book;
import teamchallenge.server.repositories.BookRepository;
import teamchallenge.server.services.AuthorService;
import teamchallenge.server.services.BookService;
import teamchallenge.server.services.CategoryService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Override
    public void createBook(CreateBookDto createBookDto) {
        Book book = new Book();
        book.setTitle(createBookDto.getTitle());
        book.setCategories(categoryService.getCategories(createBookDto.getCategories()));
        book.setAuthors(authorService.getAuthors(createBookDto.getAuthors()));
        book.setDescription(createBookDto.getDescription());
        book.setYear(createBookDto.getYear());

    }
}
