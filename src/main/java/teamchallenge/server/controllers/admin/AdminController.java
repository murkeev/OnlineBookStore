package teamchallenge.server.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.dto.ResponseBookDto;
import teamchallenge.server.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<ResponseBookDto> createBook(@RequestBody CreateBookDto createBookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(createBookDto));
    }

    @PostMapping("/book/{id}/images")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile images, @PathVariable Long id){
        bookService.saveImages(id,images);
        return ResponseEntity.ok("");
    }
}
