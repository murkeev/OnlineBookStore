package teamchallenge.server.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.entities.Book;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;
import teamchallenge.server.entities.User;
import teamchallenge.server.exception.BookNotFoundException;
import teamchallenge.server.exception.CartHeaderNotFoundException;
import teamchallenge.server.mappers.CartHeaderMapper;
import teamchallenge.server.repositories.BookRepository;
import teamchallenge.server.repositories.CartHeaderRepository;
import teamchallenge.server.repositories.CartItemRepository;
import teamchallenge.server.repositories.UserRepository;
import teamchallenge.server.services.CartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    private CartHeaderRepository cartHeaderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CartHeaderMapper cartHeaderMapper;

    @Override
    public List<CartHeaderDto> getAllCartHeaders() {

        return cartHeaderRepository.findAll().stream()
                .map(cartHeaderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartHeaderDto getCartHeaderById(Long id) {
        CartHeader cartHeader = cartHeaderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    public Long saveCartHeader(CartHeader cartHeader) {
        return cartHeaderRepository.save(cartHeader).getId();
    }

    @Override
    public void deleteCartHeader(Long id) {
        cartHeaderRepository.deleteById(id);
    }


    @Override
    public CartHeader createCart(User user) {
        CartHeader cartHeader = new CartHeader();
        cartHeader.setUser(user);
        return cartHeaderRepository.save(cartHeader);
        //userService.save(user);
    }

    @Override
    public CartHeaderDto getCartByUser(String email) {
        return cartHeaderMapper.toDto(userService.findByEmail(email).getCartHeader());
    }

    @Override
    @Transactional
    public CartHeaderDto addBook(Long bookId, Long quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderNotFoundException(user));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElse(new CartItem());
        cartItem.setCartHeader(cartHeader);
        cartItem.setBook(book);

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setPrice(cartItem.getQuantity() * book.getPrice());

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());

        cartItemRepository.save(cartItem);
        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    @Transactional
    public CartHeaderDto removeBook(Long bookId, Long quantity) {
        try {
            logger.info("Starting removeBook method");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getPrincipal().toString();

            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
            CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderNotFoundException(user));
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

            CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElseThrow(() -> new RuntimeException("Book in cart not found"));

            Long newQuantity = cartItem.getQuantity() - quantity;

            cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

            if (newQuantity > 0) {
                logger.info("Updating cart item: {}", cartItem.getId());
                cartItem.setQuantity(newQuantity);
                cartItem.setPrice(cartItem.getQuantity() * book.getPrice());
                cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());
                cartItemRepository.save(cartItem);
                logger.info("Updated cart item: {}", cartItem.getId());
            } else {
                logger.info(cartHeader.toString());
                logger.info("Deleting cart item: {}", cartItem.getId());
                cartItemRepository.delete(cartItem);
                cartHeader.getCartItems().remove(cartItem);
                logger.info("Deleted cart item: {}", cartItem.getId());
                logger.info(cartHeader.toString());
            }

            cartHeader = cartHeaderRepository.save(cartHeader);
            logger.info("Completed removeBook method successfully");
            return cartHeaderMapper.toDto(cartHeader);

        } catch (Exception e) {
            logger.error("Error while removing book from cart", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public CartHeaderDto removeAllBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderNotFoundException(user));

        for (CartItem cartItem : cartHeader.getCartItems()) {
            cartItemRepository.delete(cartItem);
        }
        cartHeader.getCartItems().clear();
        cartHeader.setTotalPrice((double) 0);

        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

}
