package teamchallenge.server.cart;

import lombok.Data;

@Data
public class ChangeBookQuantityInCartDto {
    Long cartHeaderId;
    Long bookId;
    Long quantity;
}
