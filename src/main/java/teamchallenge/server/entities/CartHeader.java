package teamchallenge.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CartHeader {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(
            name = "cart_header_cart_item",
            joinColumns = @JoinColumn(name = "cart_header_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id")
    )
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    private Double totalPrice;
}
