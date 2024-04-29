package teamchallenge.server.services;

import teamchallenge.server.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories(List<String> categories);

    void createCategory(String name);
}
