package bg.softuni.springadvancedquerying.services;

import java.util.Set;

public interface IngredientService {

    void deleteByName(String name);

    void updateAllPricesByIngredientsNames(Set<String> ingredients);

}
