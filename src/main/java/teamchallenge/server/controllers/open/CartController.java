package teamchallenge.server.controllers.open;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.dto.ChangeBookQuantityInCartDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.services.impl.CartServiceImpl;
import teamchallenge.server.utils.JwtUtils;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/open/cart")
public class CartController {

    private final JwtUtils jwtUtils;
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/all")
    public ResponseEntity<List<CartHeaderDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCartHeaders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartHeaderDto> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartHeaderById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCart(@RequestBody CartHeader cart) {
        return ResponseEntity.ok(cartService.saveCartHeader(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCartHeader(id);
        return ResponseEntity.ok("");
    }

    @PostMapping("/add-book")
    public ResponseEntity<CartHeaderDto> addBookToCart(@RequestBody ChangeBookQuantityInCartDto request) {
        return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity()));
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<CartHeaderDto> removeBookFromCart(@RequestBody ChangeBookQuantityInCartDto request) {
        return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity()));
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<CartHeaderDto> removeAllBooksFromCart() {
        return ResponseEntity.ok(cartService.removeAllBooks());
    }
}
