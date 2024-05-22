package teamchallenge.server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_header_id")
    private CartHeader cartHeader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Double price;

    private Long quantity;
}
