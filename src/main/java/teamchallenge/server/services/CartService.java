package teamchallenge.server.services;

import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.dto.CreateBookDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;
import teamchallenge.server.entities.User;

import java.util.List;

public interface CartService {
    public List<CartHeaderDto> getAllCartHeaders();
    public CartHeaderDto getCartHeaderById(Long id);

    public Long saveCartHeader(CartHeader cartHeader);

    public void deleteCartHeader(Long id);


    Long createCart(User user);

    CartHeaderDto getCartByUser(String email);

    CartHeaderDto addBook(Long bookId, Long quantity);

    CartHeaderDto removeBook(Long bookId, Long quantity);

    CartHeaderDto removeAllBooks();

}
