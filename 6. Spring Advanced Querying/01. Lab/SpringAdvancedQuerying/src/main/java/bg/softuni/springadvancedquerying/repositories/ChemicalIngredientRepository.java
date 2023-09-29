package bg.softuni.springadvancedquerying.repositories;

import bg.softuni.springadvancedquerying.entities.Ingredient;

import java.util.List;

public interface ChemicalIngredientRepository extends BaseIngredientRepository<Ingredient> {

    List<Ingredient> findByChemicalFormula(String chemicalFormula);

}
