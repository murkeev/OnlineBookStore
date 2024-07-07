package teamchallenge.server.book;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);
}
