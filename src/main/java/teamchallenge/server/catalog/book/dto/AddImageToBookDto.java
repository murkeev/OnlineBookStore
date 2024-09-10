package teamchallenge.server.catalog.book.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddImageToBookDto {
    private MultipartFile photo;
    private Long id;
}
