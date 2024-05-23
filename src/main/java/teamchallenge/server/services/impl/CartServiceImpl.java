package teamchallenge.server.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teamchallenge.server.entities.Book;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;
import teamchallenge.server.entities.User;
import teamchallenge.server.repositories.BookRepository;
import teamchallenge.server.repositories.CartHeaderRepository;
import teamchallenge.server.repositories.CartItemRepository;
import teamchallenge.server.repositories.UserRepository;
import teamchallenge.server.services.CartService;
import teamchallenge.server.utils.JwtUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    private CartHeaderRepository cartHeaderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    private final UserServiceImpl userService;

    private final JwtUtils jwtUtils;

    @Override
    public List<CartHeader> getAllCartHeaders() {
        return cartHeaderRepository.findAll();
    }
    @Override
    public CartHeader getCartHeaderById(Long id) {
        return cartHeaderRepository.findById(id).orElse(null);
    }
    @Override
    public CartHeader saveCartHeader(CartHeader cartHeader) {
        return cartHeaderRepository.save(cartHeader);
    }
    @Override
    public void deleteCartHeader(Long id) {
        cartHeaderRepository.deleteById(id);
    }


    @Override
    public void createCart(User user) {
        CartHeader cartHeader = new CartHeader();
        cartHeader.setUser(user);
        cartHeaderRepository.save(cartHeader);
    }
    @Override
    public List<CartItem> getCartByUser(String email) {
        return userService.findByEmail(email).getCartHeader().getCartItems();
    }
    @Override
    @Transactional
    public CartHeader addBook(Long bookId, Long quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElse(new CartItem());
        cartItem.setCartHeader(cartHeader);
        cartItem.setBook(book);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        cartItemRepository.save(cartItem);

        return cartHeader;
    }

    @Override
    @Transactional
    public CartHeader removeBook(Long bookId, Long quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElse(new CartItem());

        Long newQuantity = cartItem.getQuantity() - quantity;
        if (newQuantity > 0) {
            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItem);
        } else {
            cartItemRepository.delete(cartItem);
        }

        return cartHeader;
    }

}
