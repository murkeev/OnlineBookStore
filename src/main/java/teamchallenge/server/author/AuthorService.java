package teamchallenge.server.author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(List<String> authors);
}
