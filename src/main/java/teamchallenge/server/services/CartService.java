package teamchallenge.server.services;

import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;
import teamchallenge.server.entities.User;

import java.util.List;

public interface CartService {
    public List<CartHeader> getAllCartHeaders();
    public CartHeader getCartHeaderById(Long id);

    public CartHeader saveCartHeader(CartHeader cartHeader);

    public void deleteCartHeader(Long id);


    void createCart(User user);

    List<CartItem> getCartByUser(String email);

    CartHeader addBook(String email, Long bookId, Long quantity);

    CartHeader removeBook(String email, Long bookId, Long quantity);

}
