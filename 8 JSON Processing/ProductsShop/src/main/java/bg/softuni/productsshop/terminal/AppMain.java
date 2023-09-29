package bg.softuni.productsshop.terminal;

import bg.softuni.productsshop.domain.dtos.ProductInPriceRangeDTO;
import bg.softuni.productsshop.services.product.ProductService;
import bg.softuni.productsshop.services.seed.SeedService;
import bg.softuni.productsshop.services.user.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static bg.softuni.productsshop.common.ConstantPaths.RESOURCE_FILE_URL_OUTPUT_PRODUCTS;

@Component
public class AppMain implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final SeedService seedService;
    private Scanner scanner;
    private final Gson gson;

    @Autowired
    public AppMain(UserService userService, ProductService productService, SeedService seedService, Scanner scanner, Gson gson) {
        this.userService = userService;
        this.productService = productService;
        this.seedService = seedService;
        this.scanner = scanner;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedDatabase();
        findAllProductsByPriceBetween();
    }

    private void findAllProductsByPriceBetween() throws IOException {
        System.out.print("Enter lower price: ");
        BigDecimal lowerPrice = new BigDecimal(scanner.nextLine());

        System.out.print("Enter higher price: ");
        BigDecimal higherPrice = new BigDecimal(scanner.nextLine());

        List<ProductInPriceRangeDTO> productDTOs =
                productService.findAllByPriceBetween(lowerPrice, higherPrice);

        Path outputProducts = Path.of(RESOURCE_FILE_URL_OUTPUT_PRODUCTS);
        FileWriter fileWriter = new FileWriter(outputProducts.toFile());

        gson.toJson(productDTOs, fileWriter);
        fileWriter.close();

        System.out.println("Result saved in the output directory.");
    }

}
