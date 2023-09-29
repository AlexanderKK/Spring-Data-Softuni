package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "planes")
@DiscriminatorValue(value = "plane")
public class Plane extends Vehicle {

    private static final String PLANE_TYPE = "PLANE";

    @Basic
    private int passengerCapacity;

    public Plane() {
        super(PLANE_TYPE);
    }

    public Plane(String model, String fuelType, int passengerCapacity) {
        this();

        this.model = model;
        this.fuelType = fuelType;
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

}
