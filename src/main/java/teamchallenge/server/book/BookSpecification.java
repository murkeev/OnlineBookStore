package teamchallenge.server.book;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;


public class BookSpecification {

    private BookSpecification() {
    }


    public static Specification<Book> searchByAuthor(String author) {
        return (root, query, builder) -> {
            if (author == null || author.isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            Join<Book, Author> authorJoin = root.join("authors");
            return builder.equal(authorJoin.get("name"), author);
        };
    }

    public static Specification<Book> searchByTitle(String title) {
        return (root, query, builder) -> {
            if (title == null || title.isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.equal(root.get("title"), title);
        };
    }

    public static Specification<Book> searchByLanguage(String language) {
        return (root, query, builder) -> {
            if (language == null || language.isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.equal(root.get("language"), language);
        };
    }

    public static Specification<Book> searchByYear(Integer year) {
        return (root, query, builder) -> {
            if (year == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.equal(root.get("year"), year);
        };
    }
    public static Specification<Book> searchByPrice(Double price) {
        return (root, query, builder) -> {
            if (price == null){
                return builder.isTrue(builder.literal(true));
            }
            return builder.equal(root.get("price"), price);
        };
    }
    public static Specification<Book> searchByCategory(String category) {
        return (root, query, builder) -> {
            if (category == null || category.isEmpty()) {
                return builder.isTrue(builder.literal(true));
            }
            Join<Book, Category> categoryJoin = root.join("categories");
            return builder.equal(categoryJoin.get("name"), category);
        };
    }
}
