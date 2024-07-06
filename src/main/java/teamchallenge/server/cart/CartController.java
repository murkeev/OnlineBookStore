package teamchallenge.server.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/open/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartHeaderDto> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartHeaderById(id));
    }

    @PostMapping("/create-cart")
    public ResponseEntity<Long> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    @PostMapping("/add-book")
    public ResponseEntity<CartHeaderDto> addBookToCart(@RequestBody ChangeBookQuantityInCartDto request) {
        if (request.getCartHeaderId() != null) {
            return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity(), request.getCartHeaderId()));
        } else {
            return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity()));
        }
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<CartHeaderDto> removeBookFromCart(@RequestBody ChangeBookQuantityInCartDto request) {
        if (request.getCartHeaderId() != null) {
            return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity(), request.getCartHeaderId()));
        } else {
            return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity()));
        }
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<CartHeaderDto> removeAllBooksFromCart() {
        return ResponseEntity.ok(cartService.removeAllBooks());
    }
}
