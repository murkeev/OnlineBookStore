package teamchallenge.server.catalog.book.dto;

import lombok.Builder;
import lombok.Data;
import teamchallenge.server.catalog.image.dto.ResponseImageDto;

import java.util.List;

@Data
@Builder
public class ListResponseBookDto {
    private Long id;
    private String title;
    private int year;
    private double price;
    private int totalQuantity;
    private String language;
    private boolean isExpected;
    private List<String> authors;
    private ResponseImageDto image;
}
