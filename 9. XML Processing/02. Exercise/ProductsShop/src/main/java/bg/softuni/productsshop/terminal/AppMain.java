package bg.softuni.productsshop.terminal;

import bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto;
import bg.softuni.productsshop.domain.dtos.products.ProductInRangeWithNoBuyerDTO;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsDto;
import bg.softuni.productsshop.services.category.CategoryService;
import bg.softuni.productsshop.services.product.ProductService;
import bg.softuni.productsshop.services.seed.SeedService;
import bg.softuni.productsshop.services.user.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SeedService seedService;
    private Scanner scanner;
    private final Gson gson;
    private final JAXBContext productsContext;

    @Autowired
    public AppMain(UserService userService,
                   ProductService productService,
                   CategoryService categoryService, SeedService seedService,
                   Scanner scanner,
                   Gson gson,
                   JAXBContext productsContext) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.seedService = seedService;
        this.scanner = scanner;
        this.gson = gson;
        this.productsContext = productsContext;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedDatabase();

//        findAllProductsByPriceBetween();

//        findAllUsersWithSoldProducts();

//        findAllCategoriesByProducts();

        findAllUsersAndProducts();
    }

    private void findAllUsersAndProducts() throws Exception {
        this.userService.findAllUsersBySoldProductsCount();
    }

    private void findAllCategoriesByProducts() throws Exception {
        List<CategoryByProductsDto> categoriesByProducts = this.categoryService.findAllCategoriesByProducts();
    }

    private void findAllProductsByPriceBetween() throws Exception {
        System.out.print("Enter lower price: ");
        BigDecimal lowerPrice = new BigDecimal(scanner.nextLine());

        System.out.print("Enter higher price: ");
        BigDecimal higherPrice = new BigDecimal(scanner.nextLine());

        List<ProductInRangeWithNoBuyerDTO> productDTOs =
                productService.findAllByPriceBetween(lowerPrice, higherPrice);

        System.out.println("Result saved in the output directory.");
    }

    private void findAllUsersWithSoldProducts() throws Exception {
        List<UserWithSoldProductsDto> usersBySellingProductsBuyerNotNull =
                this.userService.findAllBySellingProductsBuyerNotNull();

//       usersBySellingProductsBuyerNotNull.forEach(System.out::println);
    }

}
