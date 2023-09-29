package bg.softuni.springautomappingexercise.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDTO {

    private String title;

    private String trailer;

    private String imageUrl;

    private Float size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public GameDTO() {}

    public GameDTO(String title, String trailer, String imageUrl, Float size, BigDecimal price, String description, LocalDate releaseDate) {
        setTitle(title);
        setTrailer(trailer);
        setImageUrl(imageUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || !Character.isUpperCase(title.charAt(0)) ||
                title.length() < 3 ||
                title.length() > 100) {
            throw new IllegalArgumentException("Not a valid title.");
        }

        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        if(trailer == null || trailer.length() != 11) {
            throw new IllegalArgumentException("Trailer ID should be exactly 11 characters.");
        }

        this.trailer = trailer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.startsWith("https://") && !imageUrl.startsWith("http://")) {
            throw new IllegalArgumentException("Link should begin with http/https.");
        }

        this.imageUrl = imageUrl;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        if(size == null || size <= 0.00) {
            throw new IllegalArgumentException("Size should be a positive number.");
        }

        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price should be a positive number.");
        }

        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.length() < 20) {
            throw new IllegalArgumentException("Description should be at least 20 characters long.");
        }

        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameDTO gameDTO;

        public Builder() {
            this.gameDTO = new GameDTO();
        }

        public Builder setTitle(String title) {
            gameDTO.title = title;

            return this;
        }

        public Builder setTrailer(String trailer) {
            gameDTO.trailer = trailer;

            return this;
        }

        public Builder setSize(Float size) {
            gameDTO.size = size;

            return this;
        }

        public Builder setPrice(BigDecimal price) {
            gameDTO.price = price;

            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            gameDTO.imageUrl = imageUrl;

            return this;
        }

        public Builder setDescription(String description) {
            gameDTO.description = description;

            return this;
        }

        public Builder setReleaseDate(LocalDate releaseDate) {
            gameDTO.releaseDate = releaseDate;

            return this;
        }

        public GameDTO build() {
            return gameDTO;
        }

    }

}
