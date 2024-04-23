package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.CartHeader;

@Repository
public interface CartRepository extends JpaRepository<CartHeader, Long> {
}
