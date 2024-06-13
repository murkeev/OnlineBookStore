package teamchallenge.server.exception;

import teamchallenge.server.entities.User;

public class CartHeaderNotFoundException extends RuntimeException {
    public CartHeaderNotFoundException(User user) {
        super("Cart for user " + user.toString() + " not found");
    }
}
