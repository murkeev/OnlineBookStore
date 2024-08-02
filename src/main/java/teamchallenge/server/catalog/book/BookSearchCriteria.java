package teamchallenge.server.catalog.book;

import lombok.Data;

@Data
public class BookSearchCriteria {
    private String author;
    private String title;
    private String language;
    private Integer year;
    private Double price;
    private String category;
}
