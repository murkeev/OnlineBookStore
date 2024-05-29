package teamchallenge.server.dto;

import lombok.Data;

@Data
public class ChangeBookQuantityInCartDto {
    Long bookId;
    Long quantity;
}
