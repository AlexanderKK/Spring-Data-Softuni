package bg.softuni.productsshop.config;

import bg.softuni.productsshop.domain.dtos.categories.wrappers.CategoriesImportWrapperDTO;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsImportWrapperDTO;
import bg.softuni.productsshop.domain.dtos.users.wrappers.UsersImportWrapperDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean(name = "usersContext")
    public JAXBContext createUsersContext() throws JAXBException {
        return JAXBContext.newInstance(UsersImportWrapperDTO.class);
    }

    @Bean(name = "categoriesContext")
    public JAXBContext createCategoriesContext() throws JAXBException {
        return JAXBContext.newInstance(CategoriesImportWrapperDTO.class);
    }

    @Bean(name = "productsContext")
    public JAXBContext createProductsContext() throws JAXBException {
        return JAXBContext.newInstance(ProductsImportWrapperDTO.class);
    }

    @Bean
    public ModelMapper createMapper() {
        return new ModelMapper();
    }

}
