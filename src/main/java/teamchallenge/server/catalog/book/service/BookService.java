package teamchallenge.server.catalog.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;

import java.util.List;

public interface BookService {
    ResponseBookDto createBook(CreateBookDto createBookDto);

    void saveImages(Long id, MultipartFile images);

    ResponseBookDto getBookById(Long id);

    Page<ListResponseBookDto> getBooks(Pageable pageable, Long category);

    Page<ListResponseBookDto> getBooks(Pageable pageable, String category);

    List<ResponseBookDto> searchBooks(BookSearchCriteria criteria);
}
