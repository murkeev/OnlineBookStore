package teamchallenge.server.catalog.author.service;

import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.category.entity.Category;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);

    List<Author> getAllAuthors();

    List<Author> getAuthorsByName(List<String> authorNames);

    Author addAuthor(String authorName);

    void deleteAuthor(Long authorId);

}
