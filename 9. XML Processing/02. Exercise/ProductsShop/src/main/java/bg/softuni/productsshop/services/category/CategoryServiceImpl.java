package bg.softuni.productsshop.services.category;

import bg.softuni.productsshop.common.Utils;
import bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto;
import bg.softuni.productsshop.domain.dtos.categories.wrappers.CategoriesByProductsWrapperDto;
import bg.softuni.productsshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.productsshop.common.ConstantPaths.OUTPUT_CATEGORIES_BY_PRODUCTS_XML_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryByProductsDto> findAllCategoriesByProducts() throws JAXBException {
        List<CategoryByProductsDto> categoriesDto =
                this.categoryRepository.findCategoriesByProducts()
                        .orElseThrow(NoSuchElementException::new)
                        .stream()
                        .map(category -> mapper.map(category, CategoryByProductsDto.class))
                        .toList();

        CategoriesByProductsWrapperDto categoriesWrapper =
                new CategoriesByProductsWrapperDto(categoriesDto);

        Utils.writeXmlIntoFile(categoriesWrapper, OUTPUT_CATEGORIES_BY_PRODUCTS_XML_PATH);

        return categoriesDto;
    }

}
