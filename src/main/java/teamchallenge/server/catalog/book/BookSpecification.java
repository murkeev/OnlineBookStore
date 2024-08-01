package teamchallenge.server.catalog.book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.book.entity.Book;
import teamchallenge.server.catalog.category.entity.Category;

import java.util.List;
import java.util.stream.Collectors;


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

//    filters   ***************************************************************************************


    public static Specification<Book> hasCategoryIn(List<String> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            //return root.get("categories").in(categories);

            // Приведение всех категорий к нижнему регистру
            List<String> lowerCaseCategories = categories.stream()
                    .map(cat -> cat.replace("+", " ").toLowerCase())
                    .collect(Collectors.toList());

            Join<Book, Category> categoryJoin = root.join("categories");

            // Создаем критерий "in" для проверки наличия значения в списке
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(categoryJoin.get("name")));
            for (String category : lowerCaseCategories) {
                inClause.value(category);
            }

            return inClause;
        };
    }

    public static Specification<Book> hasAuthorIn(List<String> authors) {
        return (root, query, criteriaBuilder) -> {
            if (authors == null || authors.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("authors").in(authors);
        };
    }

    public static Specification<Book> hasLanguageIn(List<String> languages) {
        return (root, query, criteriaBuilder) -> {
            if (languages == null || languages.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("languages").in(languages);
        };
    }

    public static Specification<Book> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null || maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<Book> hasYearBetween(Long minYear, Long maxYear) {
        return (root, query, criteriaBuilder) -> {
            if (minYear == null || maxYear == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("year"), minYear, maxYear);
        };
    }

    public static Specification<Book> isExpected(Boolean expected) {
        return (root, query, criteriaBuilder) -> {
            if (expected == null) {
                return criteriaBuilder.conjunction();
            }
            return root.get("isExpected").in(expected);
        };
    }

}
