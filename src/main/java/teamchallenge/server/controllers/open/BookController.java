package teamchallenge.server.controllers.open;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchallenge.server.dto.ListResponseBookDto;
import teamchallenge.server.dto.ResponseBookDto;
import teamchallenge.server.services.BookService;

@RestController
@RequestMapping("api/open/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBooks(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById((id)));
    }

    @PostMapping("/_list")
    public ResponseEntity<Page<ListResponseBookDto>> getBooks(Pageable pageable){
        return ResponseEntity.ok(bookService.getBooks(pageable));
    }
}
