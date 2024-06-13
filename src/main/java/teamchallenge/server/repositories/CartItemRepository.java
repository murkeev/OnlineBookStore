package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.Book;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartHeaderAndBook(CartHeader cartHeader, Book book);
}
