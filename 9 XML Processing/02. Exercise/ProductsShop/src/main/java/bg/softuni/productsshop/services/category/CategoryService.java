package bg.softuni.productsshop.services.category;

import bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto;
import bg.softuni.productsshop.domain.entities.Category;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface CategoryService {

    List<CategoryByProductsDto> findAllCategoriesByProducts() throws JAXBException;

}
