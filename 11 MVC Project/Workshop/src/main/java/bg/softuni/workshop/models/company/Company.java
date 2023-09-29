package bg.softuni.workshop.models.company;

import bg.softuni.workshop.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Company() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
