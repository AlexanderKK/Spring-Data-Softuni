package bg.softuni.productsshop.repositories;

import bg.softuni.productsshop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal lower, BigDecimal higher);

}
