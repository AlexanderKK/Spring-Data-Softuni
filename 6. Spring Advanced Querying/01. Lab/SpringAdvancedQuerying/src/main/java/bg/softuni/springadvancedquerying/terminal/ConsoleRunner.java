package bg.softuni.springadvancedquerying.terminal;

import bg.softuni.springadvancedquerying.entities.Shampoo;
import bg.softuni.springadvancedquerying.entities.Size;
import bg.softuni.springadvancedquerying.services.IngredientService;
import bg.softuni.springadvancedquerying.services.ShampooService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.scanner = new Scanner(System.in);

        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
//        printAllShampoosBySizeOrderById();
//        printAllShampoosBySizeOrLabelIdOrderByPrice();
//        printAllShampoosByPriceHigherOrderByPriceDesc();
//        printShampoosCountByPriceLower();
//        printAllShampoosByIngredients();
//        printAllByIngredientsCountLessThan();
//        deleteIngredientByName();
        updateAllPricesByIngredientsNames();
    }

    private void printAllShampoosBySizeOrderById() {
        String sizeType = scanner.nextLine();

        Size size = Size.valueOf(sizeType.toUpperCase());

        Set<Shampoo> shampoosBySize = shampooService.findAllBySizeOrderById(size);

        shampoosBySize.forEach(System.out::println);
    }

    private void printAllShampoosBySizeOrLabelIdOrderByPrice() {
        Size sizeOfShampoo = Size.valueOf(scanner.nextLine().toUpperCase());
        long labelId = Long.parseLong(scanner.nextLine());

        Set<Shampoo> shampoosBySizeOrLabelId = shampooService.findAllBySizeOrLabelIdOrderByPrice(sizeOfShampoo, labelId);

        shampoosBySizeOrLabelId.forEach(System.out::println);
    }

    private void printAllShampoosByPriceHigherOrderByPriceDesc() {
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

        Set<Shampoo> shampoosByPriceHigher = shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price);

        shampoosByPriceHigher.forEach(System.out::println);
    }

    private void printShampoosCountByPriceLower() {
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

        long shampoosCount = shampooService.countByPriceLessThan(price);

        System.out.println(shampoosCount);
    }

    private void printAllShampoosByIngredients() {
        Set<String> ingredients = new HashSet<>();

        String ingredient;
        while(!(ingredient = scanner.nextLine()).trim().isEmpty()) {
            ingredients.add(ingredient);
        }

        Set<Shampoo> shampoosByIngredients = shampooService.findAllByIngredients(ingredients);

        shampoosByIngredients.forEach(System.out::println);
    }

    private void printAllByIngredientsCountLessThan() {
        int ingredientsCount = Integer.parseInt(scanner.nextLine());

        List<Shampoo> shampoosByIngredientsCount = shampooService.findAllByIngredientsCountLessThan(ingredientsCount);

        shampoosByIngredientsCount.forEach(System.out::println);
    }

    private void deleteIngredientByName() {
        String name = scanner.nextLine();

        this.ingredientService.deleteByName(name);
    }

    private void updateAllPricesByIngredientsNames() {
        Set<String> ingredients = new HashSet<>();

        String ingredient;
        while(!(ingredient = scanner.nextLine()).trim().isEmpty()) {
            ingredients.add(ingredient);
        }

        this.ingredientService.updateAllPricesByIngredientsNames(ingredients);
    }

}
