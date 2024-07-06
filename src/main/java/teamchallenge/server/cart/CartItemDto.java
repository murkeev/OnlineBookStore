package teamchallenge.server.cart;

import lombok.Data;

@Data
public class CartItemDto {
    Long cartItemId;
    Long bookId;
    Long quantity;
    Double price;
}
