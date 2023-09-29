package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;

import java.util.Optional;
import java.util.Set;

// TODO:
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

    Optional<Set<Forecast>> findAllByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(
            DayOfWeek day, Integer population);

    Optional<Forecast> findFirstByDayOfWeekAndCity(DayOfWeek day, City city);

}
