package teamchallenge.server.catalog.category.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import teamchallenge.server.catalog.category.exception.CategoryNotFoundException;
import teamchallenge.server.catalog.category.entity.Category;
import teamchallenge.server.catalog.category.entity.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories(List<String> categories) {

        List<Category> result = new ArrayList<>();

        for (String categoryName : categories) {
            Category category = categoryRepository.findByName(WordUtils.capitalize(categoryName.toLowerCase())).orElse(createAndGetCategory(categoryName));
            result.add(category);
        }
        return result;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    private Category createAndGetCategory(String name) {
        Category category = new Category();
        category.setName(WordUtils.capitalize(name.toLowerCase()));
        categoryRepository.save(category);
        return category;
    }

    public void createCategory(String name) {
        // TODO document why this method is empty
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
}
