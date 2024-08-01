package teamchallenge.server.catalog.book.dto;

import lombok.Builder;
import lombok.Data;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.image.dto.ResponseImageDto;

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
    private List<String> languages;
    private List<String> authors;
    private List<Category> categories;
    private ResponseImageDto image;
}
