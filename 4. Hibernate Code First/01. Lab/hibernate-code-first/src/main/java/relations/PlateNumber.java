package relations;

import jakarta.persistence.*;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    @OneToOne(targetEntity = Truck.class, mappedBy = "plateNumber")
    private Truck truck;

    public PlateNumber() {}

    public PlateNumber(String number) {
        this.number = number;
    }

}
