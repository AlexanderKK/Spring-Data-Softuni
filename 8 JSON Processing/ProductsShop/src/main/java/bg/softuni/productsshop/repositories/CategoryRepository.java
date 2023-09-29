package bg.softuni.productsshop.repositories;

import bg.softuni.productsshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT DISTINCT * FROM product_shop.categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Category getRandomCategory();
}
