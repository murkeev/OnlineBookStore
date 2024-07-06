package teamchallenge.server.cart;

import teamchallenge.server.user.User;

public class CartHeaderForUserNotFoundException extends RuntimeException {
    public CartHeaderForUserNotFoundException(User user) {
        super("Cart for user " + user.toString() + " not found");
    }
}
