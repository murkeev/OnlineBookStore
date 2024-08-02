package teamchallenge.server.catalog.language.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.catalog.author.entity.Author;

import java.util.Optional;


@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String languageName);
}
