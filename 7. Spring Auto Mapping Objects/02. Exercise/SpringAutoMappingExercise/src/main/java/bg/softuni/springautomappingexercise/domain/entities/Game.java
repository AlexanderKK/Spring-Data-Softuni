package bg.softuni.springautomappingexercise.domain.entities;

import bg.softuni.springautomappingexercise.domain.dtos.GameDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(name="trailer_id")
    private String trailer;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Float size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    public Game() {}

    public Game(String title,
                String trailer,
                String imageThumbnail,
                Float size,
                BigDecimal price,
                String description,
                LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageUrl = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageThumbnail) {
        this.imageUrl = imageThumbnail;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public GameDTO toDTO() {
        return new GameDTO(title, trailer, imageUrl, size, price, description, releaseDate);
    }

}
