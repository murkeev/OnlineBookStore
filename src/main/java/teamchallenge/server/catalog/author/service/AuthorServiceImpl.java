package teamchallenge.server.catalog.author.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.author.entity.AuthorRepository;
import teamchallenge.server.catalog.book.entity.BookRepository;
import teamchallenge.server.catalog.category.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> getAuthors(List<String> authors) {
        List<Author> result = new ArrayList<>();

        for (String authorName : authors) {
            Author author = authorRepository.findByName(authorName).orElse(createAndGetAuthor(authorName));
            result.add(author);
        }
        return result;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    private Author createAndGetAuthor(String name) {
        Author author = new Author();
        author.setName(WordUtils.capitalizeFully(name.toLowerCase()));
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<Author> getAuthorsByName(List<String> authorNames) {
        List<Author> authors = new ArrayList<>();
        for (String name : authorNames) {
            Author author = authorRepository.findByName(name).orElseThrow(() -> new RuntimeException("Author not found"));
            if (author != null) {
                authors.add(author);
            }
        }
        return authors;
    }

    @Override
    public Author addAuthor(String authorName) {
        if (authorRepository.existsByName(authorName)) {
            throw new IllegalArgumentException("Author already exist");
        }

        Author author = new Author();
        author.setName(authorName);
        return authorRepository.save(author);
    }

    // Удаление категории с проверкой привязанных книг
    @Override
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // Проверяем, есть ли книги, привязанные к категории
        if (bookRepository.existsByAuthors(author)) {
            throw new IllegalArgumentException("Cannot delete category, books are linked to it");
        }

        authorRepository.delete(author);
    }

}
