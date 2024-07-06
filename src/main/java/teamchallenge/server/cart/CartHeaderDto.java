package teamchallenge.server.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartHeaderDto {
    Long userId;
    Long cartHeaderId;
    Double totalPrice;
    List<CartItemDto> cartItems;
}
