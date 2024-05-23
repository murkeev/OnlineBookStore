package teamchallenge.server.controllers.open;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchallenge.server.dto.ChangeBookQuantityInCartDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.services.impl.CartServiceImpl;
import teamchallenge.server.utils.JwtUtils;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
public class CartController {

    private final JwtUtils jwtUtils;
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCartHeaders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartHeaderById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartHeader cart) {
        return ResponseEntity.ok(cartService.saveCartHeader(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        cartService.deleteCartHeader(id);
        return ResponseEntity.ok("");
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBookToCart(@RequestParam ChangeBookQuantityInCartDto request) {
        return ResponseEntity.ok(cartService.addBook(request.getBookId(), request.getQuantity()));
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<?> removeBookFromCart(@RequestParam ChangeBookQuantityInCartDto request) {
        return ResponseEntity.ok(cartService.removeBook(request.getBookId(), request.getQuantity()));
    }
}
