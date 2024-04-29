package teamchallenge.server.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.services.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody CreateBookDto createBookDto) {
        bookService.createBook(createBookDto);
        return ResponseEntity.ok("");
    }
}
