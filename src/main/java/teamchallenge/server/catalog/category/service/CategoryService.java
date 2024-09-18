package teamchallenge.server.catalog.category.service;

import teamchallenge.server.catalog.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(List<String> categories);

    List<Category> getAllCategories();

    void createCategory(String name);

    Category getCategoryById(Long category);

    Category getCategoryByName(String category);

    List<Category> getCategoriesByName(List<String> categoryNames);

    Category addCategory(String categoryName);

    void deleteCategory(Long categoryId);
}
