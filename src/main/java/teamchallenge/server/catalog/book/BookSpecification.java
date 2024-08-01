package teamchallenge.server.catalog.book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.book.entity.Book;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.language.entity.Language;

import java.util.Arrays;
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

            // Приведение всех авторов к нижнему регистру и замена символов '+' на пробел
            List<String> processedAuthors = authors.stream()
                    .map(author -> author.replace("+", " ").toLowerCase())
                    .collect(Collectors.toList());

            Join<Book, Author> authorJoin = root.join("authors");

            // Создаем критерий "in" для проверки наличия значения в списке
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(authorJoin.get("name")));
            for (String author : processedAuthors) {
                inClause.value(author);
            }

            return inClause;
        };
    }

    public static Specification<Book> hasLanguageIn(List<String> languages) {
        return (root, query, criteriaBuilder) -> {
            if (languages == null || languages.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            // Приведение всех языков к нижнему регистру и замена символов '+' на пробел
            List<String> processedLanguages = languages.stream()
                    .map(language -> language.replace("+", " ").toLowerCase())
                    .collect(Collectors.toList());

            Join<Book, Language> languageJoin = root.join("languages");

            // Создаем критерий "in" для проверки наличия значения в списке
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(languageJoin.get("name")));
            for (String language : processedLanguages) {
                inClause.value(language);
            }

            return inClause;
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

    public static Specification<Book> hasYear(List<Long> years) {
        return (root, query, criteriaBuilder) -> {
            if (years == null || years.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            CriteriaBuilder.In<Long> inClause = criteriaBuilder.in(root.get("year"));
            for (Long year : years) {
                inClause.value(year);
            }
            return inClause;
        };
    }

    public static Specification<Book> isExpected(Boolean expected) {
        return (root, query, criteriaBuilder) -> {
            if (expected == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isExpected"), expected);
        };
    }

    public static Specification<Book> hasTitleContaining(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            // Приведение строки к нижнему регистру и замена символа '+' на пробел
            String[] keywords = title.replace("+", " ").toLowerCase().split("\\s+");

            // Создаем условия "like" для каждого ключевого слова и объединяем их с помощью "or"
            Predicate[] predicates = Arrays.stream(keywords)
                    .map(keyword -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("title")),
                            "%" + keyword + "%"
                    ))
                    .toArray(Predicate[]::new);

            return criteriaBuilder.and(predicates);
        };
    }

}
