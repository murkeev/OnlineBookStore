package teamchallenge.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.Book;
import teamchallenge.server.entities.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByCategories(Pageable pageable, Category category);
}
