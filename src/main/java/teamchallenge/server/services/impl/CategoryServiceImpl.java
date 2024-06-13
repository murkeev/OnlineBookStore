package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import teamchallenge.server.entities.Category;
import teamchallenge.server.exception.CategoryNotFoundException;
import teamchallenge.server.repositories.CategoryRepository;
import teamchallenge.server.services.CategoryService;

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

    }

    @Override
    public Category getCategoryById(Long category) {
        return categoryRepository.findById(category).orElseThrow(() -> new CategoryNotFoundException(category));
    }
}
