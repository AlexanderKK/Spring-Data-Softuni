package bg.softuni.springdataintroexercise.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(targetEntity = Book.class, mappedBy = "categories")
    private Set<Book> books;

}
