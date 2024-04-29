package teamchallenge.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;



@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    private String description;

    private int year;

    @OneToMany
    private List<Image> images;

    private double price;

    @Column(name = "total_quantity")
    private int totalQuantity;

    private boolean isExpected;

}
