package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Country;

import java.util.Optional;

// TODO:
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findFirstByName(String countryName);

}
