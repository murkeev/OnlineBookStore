package teamchallenge.server.catalog.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;

import java.io.IOException;
import java.time.Year;
import java.util.List;

public interface BookService {

    ResponseBookDto getBookById(Long id);

    void deleteBookById(Long id);

    Page<ListResponseBookDto> getBooks(
            Pageable pageable,
            String stringCategory,
            String stringPrice,
            String stringYear,
            String stringLanguage,
            String stringAuthor,
            String stringExpected,
            String stringTitle);

    List<ResponseBookDto> searchBooks(BookSearchCriteria criteria);

    Long addBook(MultipartFile photo, CreateBookDto createBook) throws IOException;

    Long changeImage(MultipartFile photo, Long id) throws IOException;

    List<Integer> getAllYears();
}
