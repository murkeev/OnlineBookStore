package teamchallenge.server.catalog.category.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import teamchallenge.server.catalog.book.entity.BookRepository;
import teamchallenge.server.catalog.category.exception.CategoryNotFoundException;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.category.entity.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Category> getAllCategories(List<String> categories) {

        List<Category> result = new ArrayList<>();

        for (String categoryName : categories) {
            Category category = categoryRepository.findByName(WordUtils.capitalize(categoryName.toLowerCase())).orElse(createAndGetCategory(categoryName));
            result.add(category);
        }
        return result;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private Category createAndGetCategory(String name) {
        Category category = new Category();
        category.setName(WordUtils.capitalize(name.toLowerCase()));
        categoryRepository.save(category);
        return category;
    }

    public void createCategory(String name) {

    }

    @Override
    public Category getCategoryById(Long category) {
        return categoryRepository.findById(category).orElseThrow(() -> new CategoryNotFoundException(category));
    }

    @Override
    public Category getCategoryByName(String category) {
        category = category.toLowerCase().replace('+',' ');
        String capitalized = category.substring(0, 1).toUpperCase() + category.substring(1);
        return categoryRepository.findByName(capitalized).orElseThrow(() -> new CategoryNotFoundException(capitalized));
    }

    @Override
    public List<Category> getCategoriesByName(List<String> categoryNames) {
        List<Category> categories = new ArrayList<>();
        for (String name : categoryNames) {
            Category category = categoryRepository.findByName(name).orElseThrow(() -> new RuntimeException("Category not found"));
            if (category != null) {
                categories.add(category);
            }
        }
        return categories;
    }

    // Добавление категории
    @Override
    public Category addCategory(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Category already exist");
        }

        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    // Удаление категории с проверкой привязанных книг
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Проверяем, есть ли книги, привязанные к категории
        if (bookRepository.existsByCategories(category)) {
            throw new IllegalArgumentException("Cannot delete category, books are linked to it");
        }

        categoryRepository.delete(category);
    }
}
