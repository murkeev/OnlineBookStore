package teamchallenge.server.dto;

import lombok.Builder;
import lombok.Data;
import teamchallenge.server.entities.Author;
import teamchallenge.server.entities.Image;

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
        private List<String> authors;
        private List<String> categories;
        private ResponseImageDto image;
}
