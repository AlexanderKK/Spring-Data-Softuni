package bg.softuni.productsshop.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Category() {}

    public Category(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < 3 || name.length() > 15) {
            throw new IllegalArgumentException("Incorrect name.");
        }

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name) &&
               Objects.equals(this.getId(), ((Category) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, this.getId());
    }

}
