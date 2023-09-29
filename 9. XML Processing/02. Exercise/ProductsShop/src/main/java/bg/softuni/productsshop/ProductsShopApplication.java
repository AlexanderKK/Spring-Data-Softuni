package bg.softuni.productsshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProductsShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsShopApplication.class, args);
    }

}
