package teamchallenge.server.cart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teamchallenge.server.book.Book;
import teamchallenge.server.book.BookNotFoundException;
import teamchallenge.server.book.BookRepository;
import teamchallenge.server.user.User;
import teamchallenge.server.user.UserRepository;
import teamchallenge.server.user.UserServiceImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartHeaderRepository cartHeaderRepository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final UserServiceImpl userService;

    private final CartHeaderMapper cartHeaderMapper;

    @Override
    public List<CartHeaderDto> getAllCartHeaders() {

        return cartHeaderRepository.findAll().stream()
                .map(cartHeaderMapper::toDto)
                .toList();
    }

    @Override
    public CartHeaderDto getCartHeaderById(Long id) {
        CartHeader cartHeader = cartHeaderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    public CartHeader getCartHeaderEntityById(Long id) {
        return cartHeaderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
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
    public Long createCart() {
        CartHeader cartHeader = new CartHeader();
        return cartHeaderRepository.save(cartHeader).getId();
        //userService.save(user);
    }

    @Override
    public CartHeader addUserToCart(User user, Long cartHeaderId) {
        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
        cartHeader.setUser(user);
        return cartHeaderRepository.save(cartHeader);
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
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));
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

    public CartHeaderDto addBook(Long bookId, Long quantity, Long cartHeaderId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getPrincipal().toString();
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
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
            CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));
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
    public CartHeaderDto removeBook(Long bookId, Long quantity, Long cartHeaderId) {
        try {
            logger.info("Starting removeBook method");
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String email = authentication.getPrincipal().toString();
//            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

            CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
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
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));

        for (CartItem cartItem : cartHeader.getCartItems()) {
            cartItemRepository.delete(cartItem);
        }
        cartHeader.getCartItems().clear();
        cartHeader.setTotalPrice((double) 0);

        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    @Transactional
    public CartHeaderDto removeAllBooks(Long cartHeaderId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getPrincipal().toString();
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));

        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));

        for (CartItem cartItem : cartHeader.getCartItems()) {
            cartItemRepository.delete(cartItem);
        }
        cartHeader.getCartItems().clear();
        cartHeader.setTotalPrice((double) 0);

        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

}
