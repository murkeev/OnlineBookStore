package teamchallenge.server.cart.dto;

import lombok.Data;

@Data
public class ChangeBookQuantityInCartDto {
    Long cartHeaderId;
    Long bookId;
    Long quantity;
}
