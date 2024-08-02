package teamchallenge.server.cart.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.cart.dto.CartHeaderDto;
import teamchallenge.server.cart.entity.CartHeader;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartHeaderMapper {
    private final CartItemMapper cartItemMapper;

    public CartHeaderDto toDto(CartHeader cartHeader) {
        CartHeaderDto dto = new CartHeaderDto();
        dto.setCartHeaderId(cartHeader.getId());
        if (cartHeader.getUser() != null) {
            dto.setUserId(cartHeader.getUser().getId());
        }
        dto.setTotalPrice(cartHeader.getTotalPrice());
        dto.setCartItems(cartHeader.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
//    public CartHeader toEntity (CartHeaderDto cartHeaderDto){}
}
