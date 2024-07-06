package teamchallenge.server.cart;

public class CartHeaderNotFoundException extends RuntimeException {
    public CartHeaderNotFoundException(Long cartHeaderId) {
        super("Cart with id " + cartHeaderId.toString() + " not found");
    }
}
