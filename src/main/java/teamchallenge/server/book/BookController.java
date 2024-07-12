package teamchallenge.server.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/open/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBooks(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById((id)));
    }

    @PostMapping("/_list")
    public ResponseEntity<Page<ListResponseBookDto>> getBooks(Pageable pageable, @RequestParam(required = false) Long category) {
        return ResponseEntity.ok(bookService.getBooks(pageable, category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseBookDto>> searchBook(@ModelAttribute BookSearchCriteria bookSearchCriteria) {
        return ResponseEntity.ok(bookService.searchBooks(bookSearchCriteria));
    }
}
