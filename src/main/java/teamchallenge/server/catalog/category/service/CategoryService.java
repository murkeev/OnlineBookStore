package teamchallenge.server.catalog.category.service;

import teamchallenge.server.catalog.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories(List<String> categories);

    List<Category> getCategories();

    void createCategory(String name);

    Category getCategoryById(Long category);

    Category getCategoryByName(String category);
}
