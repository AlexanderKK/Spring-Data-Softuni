package bg.softuni.springautomappingexercise.repositories;

import bg.softuni.springautomappingexercise.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findFirstByTitle(String title);

    @Query("UPDATE Game g SET g.title = :title, g.trailer = :trailer, g.price = :price, g.size = :size, " +
            "g.imageUrl = :imageUrl, g.description = :description, g.releaseDate = :releaseDate " +
            "WHERE g.id = :id")
    @Modifying
    void updateGamesById(Long id, String title, String trailer, BigDecimal price,
                         Float size, String imageUrl, String description, LocalDate releaseDate);

    void deleteById(Long id);

}
