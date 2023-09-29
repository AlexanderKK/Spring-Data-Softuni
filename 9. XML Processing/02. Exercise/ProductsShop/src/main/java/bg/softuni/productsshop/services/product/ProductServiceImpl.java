package bg.softuni.productsshop.services.product;

import bg.softuni.productsshop.common.Utils;
import bg.softuni.productsshop.domain.dtos.products.ProductDTO;
import bg.softuni.productsshop.domain.dtos.products.ProductInRangeWithNoBuyerDTO;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsInRangeWithNoBuyerWrapperDTO;
import bg.softuni.productsshop.domain.entities.Product;
import bg.softuni.productsshop.repositories.ProductRepository;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.List;

import static bg.softuni.productsshop.common.ConstantPaths.OUTPUT_PRODUCTS_IN_RANGE_XML_PATH;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Gson gson, ModelMapper mapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetween(BigDecimal lower, BigDecimal higher) throws JAXBException {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerNullOrderByPrice(lower, higher);

        List<ProductInRangeWithNoBuyerDTO> productsDTO = products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInPriceRange)
                .toList();

        ProductsInRangeWithNoBuyerWrapperDTO productsWrapperDTO =
                new ProductsInRangeWithNoBuyerWrapperDTO(productsDTO);

        Utils.writeXmlIntoFile(productsWrapperDTO, OUTPUT_PRODUCTS_IN_RANGE_XML_PATH);

        return productsDTO;
    }

}
