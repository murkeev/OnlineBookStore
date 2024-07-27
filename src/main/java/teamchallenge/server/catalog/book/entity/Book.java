package teamchallenge.server.catalog.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.image.entity.Image;

import java.time.LocalDateTime;
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

    @Column(length = 2000)
    private String description;

    private int year;

    private String language;

    @OneToOne
    private Image images;


    private double price;

    @Column(name = "total_quantity")
    private int totalQuantity;

    private boolean isExpected;

    private Integer discount;

    @CreatedDate
    private LocalDateTime createdAt;
}
