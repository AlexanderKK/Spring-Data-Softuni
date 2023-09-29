package bg.softuni.springadvancedquerying.services;

import bg.softuni.springadvancedquerying.entities.Shampoo;
import bg.softuni.springadvancedquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {

    Set<Shampoo> findAllBySizeOrderById(Size size);

    Set<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size sizeOfShampoo, long labelId);

    Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    long countByPriceLessThan(BigDecimal price);

    Set<Shampoo> findAllByIngredients(Set<String> ingredients);

    List<Shampoo> findAllByIngredientsCountLessThan(int ingredientsCount);

}
