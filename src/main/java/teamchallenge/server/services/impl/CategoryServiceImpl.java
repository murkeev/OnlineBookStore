package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.entities.Category;
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
            Category category = categoryRepository.findByName(categoryName).orElse(createAndGetCategory(categoryName));
            result.add(category);
        }
        return result;
    }

    private Category createAndGetCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    public void createCategory(String name){

    }
}
