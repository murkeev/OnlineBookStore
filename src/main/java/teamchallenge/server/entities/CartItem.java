package teamchallenge.server.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class CartItem {
    @Id
    private Long id;

    private Long itemNumber;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Long bookId;

    private Double price;

    private Long quantity;
}
