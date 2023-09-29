package bg.softuni.productsshop.services.seed;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {

    String seedUsers() throws IOException, JAXBException;
    String seedProducts() throws IOException, JAXBException;
    String seedCategories() throws IOException, JAXBException;

    default void seedDatabase() throws IOException, JAXBException {
        System.out.println(seedUsers());
        System.out.println(seedCategories());
        System.out.println(seedProducts());
    }

}
