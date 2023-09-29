package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import static softuni.exam.constants.Messages.INVALID_COUNTRY;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Size(min = 2, max = 60)
    @Column(name = "country_name", nullable = false, unique = true)
    private String name;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String currency;

    public Country() {}

    public Country(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
