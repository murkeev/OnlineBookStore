package teamchallenge.server.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.dto.AddImageToBookDto;
import teamchallenge.server.catalog.book.service.BookService;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;
import teamchallenge.server.cart.entity.CartHeader;
import teamchallenge.server.cart.dto.CartHeaderDto;
import teamchallenge.server.cart.service.CartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final CartServiceImpl cartService;

//    @PostMapping("/book")
//    public ResponseEntity<ResponseBookDto> createBook(@RequestBody CreateBookDto createBookDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(createBookDto));
//    }

//    @PostMapping("/book/{id}/images")
//    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile images, @PathVariable Long id) {
//        bookService.saveImages(id, images);
//        return ResponseEntity.ok("");
//    }

    @GetMapping("/test2")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Test");
    }

    @GetMapping("/cart/all")
    public ResponseEntity<List<CartHeaderDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCartHeaders());
    }

    @PostMapping("/cart/create-cart")
    public ResponseEntity<Long> createCart(@RequestBody CartHeader cart) {
        return ResponseEntity.ok(cartService.saveCartHeader(cart));
    }

    @DeleteMapping("/cart/delete-cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCartHeader(id);
        return ResponseEntity.ok("");
    }

    @PostMapping("/book/add-book")
    public ResponseEntity<String> addBook(@RequestBody CreateBookDto createBook) {
        Long id;
        try {
            id = bookService.addBook(createBook.getPhoto(), createBook.getTitle(), createBook.getCategoryNames(), createBook.getAuthorNames(), createBook.getDescription(), createBook.getYear(), createBook.getLanguageNames(), createBook.getPrice(), createBook.getTotalQuantity(), createBook.isExpected(), createBook.getDiscount());
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding book: " + e.getMessage());
        }
    }

    @PostMapping("/book/add-image-to-book")
    public ResponseEntity<String> addImageToBook(@RequestBody AddImageToBookDto addImageToBook) {
        Long id;
        try {
            id = bookService.addImageToBook(addImageToBook.getPhoto(), addImageToBook.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding book: " + e.getMessage());
        }
    }

    @DeleteMapping("/book/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("");
    }
}
