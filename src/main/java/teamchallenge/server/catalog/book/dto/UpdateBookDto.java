package teamchallenge.server.catalog.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateBookDto {
    private String title;
    private String description;
    private Integer year;
    private Double price;
    private Integer totalQuantity;
    private Boolean isExpected;
    private List<String> authorNames;
    private List<String> categoryNames;
    private List<String> languageNames;
    private Integer discount;
}
