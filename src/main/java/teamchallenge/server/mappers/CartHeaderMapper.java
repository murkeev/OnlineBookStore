package teamchallenge.server.mappers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.entities.CartHeader;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartHeaderMapper {
    @Autowired
    private CartItemMapper cartItemMapper;

    public CartHeaderDto toDto(CartHeader cartHeader) {
        CartHeaderDto dto = new CartHeaderDto();
        dto.setCartHeaderId(cartHeader.getId());
        dto.setUserId(cartHeader.getUser().getId());
        dto.setTotalPrice(cartHeader.getTotalPrice());
        dto.setCartItems(cartHeader.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
//    public CartHeader toEntity (CartHeaderDto cartHeaderDto){}
}
