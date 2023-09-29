package bg.softuni.springdataintroexercise.services.category;

import bg.softuni.springdataintroexercise.models.Category;
import bg.softuni.springdataintroexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public Set<Category> getRandomCategories() {
        final long count = this.categoryRepository.count();
        if(count != 0) {
            final long randomCategoryId = new Random().nextLong(1L, count);

            return Set.of(this.categoryRepository.findById(randomCategoryId)
                    .orElseThrow(NoSuchElementException::new));
        }

        throw new RuntimeException();
    }

}
