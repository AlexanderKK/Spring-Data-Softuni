package bg.softuni.productsshop.repositories;

import bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto;
import bg.softuni.productsshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT DISTINCT * FROM product_shop_xml.categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Category getRandomCategory();

    @Query("SELECT new bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto(" +
            "c.name, COUNT(p.id), AVG(p.price), SUM(p.price)) " +
           "FROM Product AS p JOIN p.categories AS c " +
            "GROUP BY c.id ORDER BY COUNT(p.id) DESC")
    Optional<List<CategoryByProductsDto>> findCategoriesByProducts();

}
