package bg.softuni.productsshop.services.product;

import bg.softuni.productsshop.domain.dtos.ProductDTO;
import bg.softuni.productsshop.domain.dtos.ProductInPriceRangeDTO;
import bg.softuni.productsshop.domain.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductInPriceRangeDTO> findAllByPriceBetween(BigDecimal lower, BigDecimal higher);

}
