package teamchallenge.server.admin.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.admin.service.AdminService;
import teamchallenge.server.cart.service.CartService;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.author.service.AuthorService;
import teamchallenge.server.catalog.book.dto.*;
import teamchallenge.server.catalog.book.service.BookService;
import teamchallenge.server.cart.entity.CartHeader;
import teamchallenge.server.cart.dto.CartHeaderDto;
import teamchallenge.server.cart.service.CartServiceImpl;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.category.service.CategoryService;
import teamchallenge.server.catalog.language.entity.Language;
import teamchallenge.server.catalog.language.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final CartService cartService;
    private final AdminService adminService;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final LanguageService languageService;


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
    public ResponseEntity<String> addBook(
            @RequestParam("photo") MultipartFile photo,
            @ModelAttribute CreateBookDto createBook) {
        Long id;
        try {
            id = bookService.addBook(photo, createBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding book: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable Long id,
            @RequestBody UpdateBookDto updateBookDto) {
        // Вызов сервиса для обновления книги
        Long updatedBookId = bookService.updateBook(id, updateBookDto);

        // Возвращаем успешный ответ с ID обновленной книги
        return ResponseEntity.ok("Book updated successfully with ID: " + updatedBookId);
    }

    @PostMapping("/book/change-image")
    public ResponseEntity<String> addImageToBook(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam("id") Long id) {
        try {
            Long bookId = bookService.changeImage(photo, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookId.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding image to book: " + e.getMessage());
        }
    }

    @DeleteMapping("/book/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("");
    }

    @GetMapping("/book/get-all-lists")
    public ResponseEntity<ResponseListsDto> getAllLists() {
        return ResponseEntity.ok(adminService.getAllLists());
    }

    @PostMapping("/category/add")
    public ResponseEntity<String> addCategory(@RequestParam String name) {
        try {
            Category category = categoryService.addCategory(name);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category added: " + category.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/category/edit/{id}")
    public ResponseEntity<String> editCategory(@PathVariable Long id, @RequestParam String name) {
        try {
            categoryService.editCategory(id, name);
            return ResponseEntity.status(HttpStatus.OK).body("Category edited");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/author/add")
    public ResponseEntity<String> addAuthor(@RequestParam String name) {
        try {
            Author author = authorService.addAuthor(name);
            return ResponseEntity.status(HttpStatus.CREATED).body("Author added: " + author.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Author deleted");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/author/edit/{id}")
    public ResponseEntity<String> editAuthor(@PathVariable Long id, @RequestParam String name) {
        try {
            authorService.editAuthor(id, name);
            return ResponseEntity.status(HttpStatus.OK).body("Author edited");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/language/add")
    public ResponseEntity<String> addLanguage(@RequestParam String name) {
        try {
            Language language = languageService.addLanguage(name);
            return ResponseEntity.status(HttpStatus.CREATED).body("Language added: " + language.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/language/delete/{id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable Long id) {
        try {
            languageService.deleteLanguage(id);
            return ResponseEntity.status(HttpStatus.OK).body("Language deleted");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/language/edit/{id}")
    public ResponseEntity<String> editLanguage(@PathVariable Long id, @RequestParam String name) {
        try {
            languageService.editLanguage(id, name);
            return ResponseEntity.status(HttpStatus.OK).body("Language edited");
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
