package teamchallenge.server.cart.mapper;


import org.springframework.stereotype.Service;
import teamchallenge.server.cart.dto.CartItemDto;
import teamchallenge.server.cart.entity.CartItem;

@Service
public class CartItemMapper {
    public CartItemDto toDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setCartItemId(cartItem.getId());
        dto.setBookId(cartItem.getBook().getId());
        dto.setPrice(cartItem.getPrice());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

//    public CartItem toEntity (CartItemDto cartItemDto){}
}
