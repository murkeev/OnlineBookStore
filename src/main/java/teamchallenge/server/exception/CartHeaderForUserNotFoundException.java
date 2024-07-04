package teamchallenge.server.exception;

import teamchallenge.server.entities.User;

public class CartHeaderForUserNotFoundException extends RuntimeException {
    public CartHeaderForUserNotFoundException(User user) {
        super("Cart for user " + user.toString() + " not found");
    }
}
