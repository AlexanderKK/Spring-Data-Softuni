package bg.softuni.springadvancedquerying.repositories;

import bg.softuni.springadvancedquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    void deleteByName(String name);

    @Query("UPDATE Ingredient AS i SET i.price = i.price * CAST(1.10 AS BIGDECIMAL) WHERE i.name IN :ingredientNames")
    @Modifying
    void updateAllPricesByIngredientNames(@Param("ingredientNames") Set<String> ingredients);

}
