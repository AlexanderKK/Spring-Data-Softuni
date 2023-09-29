package bg.softuni.productsshop.services.product;

import bg.softuni.productsshop.domain.dtos.products.ProductInRangeWithNoBuyerDTO;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetween(BigDecimal lower, BigDecimal higher) throws JAXBException;

}
