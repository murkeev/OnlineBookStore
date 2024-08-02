package teamchallenge.server.cart.dto;

import lombok.Data;

@Data
public class CartItemDto {
    Long cartItemId;
    Long bookId;
    Long quantity;
    Double price;
}
