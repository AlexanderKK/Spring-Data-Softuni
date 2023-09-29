package bg.softuni.springdataintroexercise.services.category;

import bg.softuni.springdataintroexercise.models.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<Category> categories);

    boolean isDataSeeded();

    Set<Category> getRandomCategories();

}
