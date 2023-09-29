package bg.softuni.springadvancedquerying.repositories;

import bg.softuni.springadvancedquerying.entities.Shampoo;
import bg.softuni.springadvancedquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    Optional<Set<Shampoo>> findAllBySizeOrderById(Size size);

    Optional<Set<Shampoo>> findAllBySizeOrLabelIdOrderByPrice(Size size, long labelId);

    Optional<Set<Shampoo>> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Optional<Long> countByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients AS i WHERE i.name IN (:ingredientNames) GROUP BY s.brand")
    Optional<Set<Shampoo>> findAllByIngredients(@Param(value = "ingredientNames") Set<String> ingredients);

    @Query("SELECT s FROM Shampoo AS s JOIN s.ingredients AS i GROUP BY s.brand HAVING COUNT(i.id) < :ingredientsCount")
    Optional<List<Shampoo>> findAllByIngredientsCountLessThan(int ingredientsCount);

}
