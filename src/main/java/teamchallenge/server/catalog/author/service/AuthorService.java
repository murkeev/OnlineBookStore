package teamchallenge.server.catalog.author.service;

import teamchallenge.server.catalog.author.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);
}
