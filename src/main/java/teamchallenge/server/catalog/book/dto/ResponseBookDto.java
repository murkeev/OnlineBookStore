package teamchallenge.server.catalog.book.dto;

import lombok.Builder;
import lombok.Data;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.language.entity.Language;

import java.util.List;

@Data
@Builder
public class ResponseBookDto {
    private Long id;
    private String title;
    private String description;
    private int year;
    private double price;
    private int totalQuantity;
    private boolean isExpected;
    private List<Language> languages;
    private List<Author> authors;
    private List<Category> categories;
    private String imageUrl;
    private Integer discount;
    private double discountPrice;
}
