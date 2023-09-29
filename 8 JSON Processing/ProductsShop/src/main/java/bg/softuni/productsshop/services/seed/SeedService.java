package bg.softuni.productsshop.services.seed;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {

    String seedUsers() throws IOException;
    String seedProducts() throws IOException;
    String seedCategories() throws IOException;

    default void seedDatabase() throws IOException {
        System.out.println(seedUsers());
        System.out.println(seedCategories());
        System.out.println(seedProducts());
    }

}
