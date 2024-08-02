package teamchallenge.server.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import teamchallenge.server.user.entity.User;

import java.util.List;

@Entity
@Data
public class CartHeader {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "cartHeader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    private Double totalPrice = (double) 0;
}
