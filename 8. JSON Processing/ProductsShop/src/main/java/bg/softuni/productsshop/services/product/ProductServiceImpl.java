package bg.softuni.productsshop.services.product;

import bg.softuni.productsshop.domain.dtos.ProductDTO;
import bg.softuni.productsshop.domain.dtos.ProductInPriceRangeDTO;
import bg.softuni.productsshop.domain.entities.Product;
import bg.softuni.productsshop.repositories.ProductRepository;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ProductInPriceRangeDTO> findAllByPriceBetween(BigDecimal lower, BigDecimal higher) {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerNullOrderByPrice(lower, higher);

        return products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInPriceRange)
                .toList();
    }

}
