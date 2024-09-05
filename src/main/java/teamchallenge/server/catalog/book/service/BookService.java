package teamchallenge.server.catalog.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;

import java.io.IOException;
import java.util.List;

public interface BookService {
//    ResponseBookDto createBook(CreateBookDto createBookDto);

//    void saveImages(Long id, MultipartFile images);

    ResponseBookDto getBookById(Long id);

    //Page<ListResponseBookDto> getBooks(Pageable pageable, Long category);

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

    void addBook(MultipartFile photo, String title, List<String> categoryNames, List<String> authorNames,
                 String description, int year, List<String> languageNames, double price,
                 int totalQuantity, boolean isExpected, Integer discount) throws IOException;
}
