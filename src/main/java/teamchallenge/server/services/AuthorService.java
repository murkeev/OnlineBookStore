package teamchallenge.server.services;

import teamchallenge.server.entities.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);
}
