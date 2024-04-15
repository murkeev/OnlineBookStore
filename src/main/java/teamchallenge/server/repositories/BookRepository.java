package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
