package bg.softuni.springautomappingexercise.repositories;

import bg.softuni.springautomappingexercise.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
