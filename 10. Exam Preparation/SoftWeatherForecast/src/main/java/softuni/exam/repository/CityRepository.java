package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findCityByName(String name);

}
