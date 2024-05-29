package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.User;

import java.util.Optional;

@Repository
public interface CartHeaderRepository extends JpaRepository<CartHeader, Long> {
    Optional<CartHeader> findByUser(User user);
}
