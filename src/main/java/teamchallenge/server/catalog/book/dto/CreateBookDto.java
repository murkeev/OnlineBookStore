package teamchallenge.server.catalog.book.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateBookDto {
    private String title;
    private List<String> categories;
    private List<String> authors;
    private String description;
    private int year;
    private List<MultipartFile> images;
    private double price;
    private int totalQuantity;
    private boolean isExpected;
    private List<String> languages;
    private Integer discount;
}
