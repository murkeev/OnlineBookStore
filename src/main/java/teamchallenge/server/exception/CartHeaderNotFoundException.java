package teamchallenge.server.exception;

import teamchallenge.server.entities.User;

public class CartHeaderNotFoundException extends RuntimeException {
    public CartHeaderNotFoundException(Long cartHeaderId) {
        super("Cart with id " + cartHeaderId.toString() + " not found");
    }
}
