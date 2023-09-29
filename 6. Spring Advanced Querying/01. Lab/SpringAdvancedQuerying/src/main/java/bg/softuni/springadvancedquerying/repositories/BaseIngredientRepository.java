package bg.softuni.springadvancedquerying.repositories;

import bg.softuni.springadvancedquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseIngredientRepository<T extends Ingredient> extends JpaRepository<T, Long> {
    
}
