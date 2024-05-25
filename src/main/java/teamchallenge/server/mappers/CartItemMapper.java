package teamchallenge.server.mappers;


import org.springframework.stereotype.Service;
import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.dto.CartItemDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.CartItem;

@Service
public class CartItemMapper {
    public CartItemDto toDto (CartItem cartItem){
        CartItemDto dto = new CartItemDto();
        dto.setCartItemId(cartItem.getId());
        dto.setBookId(cartItem.getBook().getId());
        dto.setPrice(cartItem.getPrice());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

//    public CartItem toEntity (CartItemDto cartItemDto){}
}
