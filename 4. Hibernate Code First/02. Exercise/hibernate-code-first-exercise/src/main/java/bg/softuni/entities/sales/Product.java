package bg.softuni.entities.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Double quantity;

    @Column
    private BigDecimal price;

    @OneToMany(targetEntity = Sale.class, mappedBy = "product")
    private Set<Sale> sales;

}
