package teamchallenge.server.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    ResponseBookDto createBook(CreateBookDto createBookDto);

    void saveImages(Long id, MultipartFile images);

    ResponseBookDto getBookById(Long id);

    Page<ListResponseBookDto> getBooks(Pageable pageable, Long category);

    List<ResponseBookDto> searchBooks(BookSearchCriteria criteria);
}
