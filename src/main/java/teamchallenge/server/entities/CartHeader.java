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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart_header", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    private Double totalPrice;
}
