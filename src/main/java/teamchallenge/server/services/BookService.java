package teamchallenge.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.dto.ListResponseBookDto;
import teamchallenge.server.dto.ResponseBookDto;

import java.util.List;

public interface BookService {
    ResponseBookDto createBook(CreateBookDto createBookDto);

    void saveImages(Long id, MultipartFile images);

    ResponseBookDto getBookById(Long id);

    Page<ListResponseBookDto> getBooks(Pageable pageable);
}
