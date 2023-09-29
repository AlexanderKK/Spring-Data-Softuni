package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Size(min = 2, max = 60)
    @Column(name = "city_name", nullable = false, unique = true)
    private String name;

    @Size(min = 2)
    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 500)
    @Column(nullable = false)
    private Integer population;

    @ManyToOne
    private Country country;

    public City() {}

    public City(String name, String description, Integer population, Country country) {
        this.name = name;
        this.description = description;
        this.population = population;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
