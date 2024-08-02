package teamchallenge.server.cart.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.catalog.book.entity.Book;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartHeaderAndBook(CartHeader cartHeader, Book book);
}
