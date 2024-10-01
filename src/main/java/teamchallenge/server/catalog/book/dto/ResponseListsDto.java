package teamchallenge.server.catalog.book.dto;

import lombok.Data;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.language.entity.Language;

import java.util.List;

@Data
public class ResponseListsDto {
    private List<Language> languages;
    private List<Author> authors;
    private List<Category> categories;
    private List<Integer> years;
}
