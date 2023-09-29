package bg.softuni.entities.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    private Set<Sale> sales;

}
