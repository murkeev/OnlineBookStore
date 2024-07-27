package teamchallenge.server.cart.exception;

import teamchallenge.server.user.entity.User;

public class CartHeaderForUserNotFoundException extends RuntimeException {
    public CartHeaderForUserNotFoundException(User user) {
        super("Cart for user " + user.toString() + " not found");
    }
}
