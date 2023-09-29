package bg.softuni.springadvancedqueryingexercise.service;

import bg.softuni.springadvancedqueryingexercise.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
