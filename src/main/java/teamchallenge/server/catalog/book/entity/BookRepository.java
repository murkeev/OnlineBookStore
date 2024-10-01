package teamchallenge.server.catalog.book.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.language.entity.Language;

import java.time.Year;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Page<Book> findAllByCategories(Category category, Pageable pageable);
    boolean existsByCategories(Category category);
    boolean existsByAuthors(Author author);
    boolean existsByLanguages(Language language);

    @Query("SELECT DISTINCT b.year FROM Book b ORDER BY b.year ASC")
    List<Integer> findAllYears();
}
