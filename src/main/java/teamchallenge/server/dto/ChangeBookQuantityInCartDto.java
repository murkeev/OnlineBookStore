package teamchallenge.server.dto;

import lombok.Data;

@Data
public class ChangeBookQuantityInCartDto {
    String token;
    Long productId;
    Long quantity;
}
