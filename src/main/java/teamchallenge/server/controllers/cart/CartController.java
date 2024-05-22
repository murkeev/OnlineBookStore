package teamchallenge.server.controllers.cart;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.services.impl.CartServiceImpl;
import teamchallenge.server.utils.JwtUtils;

import java.util.List;

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
    public ResponseEntity<?> addProductToCart(@RequestParam String token, @RequestParam Long productId, @RequestParam Long quantity) {
        String email = jwtUtils.getEmail(token);
        return ResponseEntity.ok(cartService.addBook(email, productId, quantity));
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<?> removeProductFromCart(@RequestParam String token, @RequestParam Long productId, @RequestParam Long quantity) {
        String email = jwtUtils.getEmail(token);
        return ResponseEntity.ok(cartService.removeBook(email, productId, quantity));
    }
}
