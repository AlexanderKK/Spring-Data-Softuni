package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {

    @Basic
    protected int seats;

    protected PassengerVehicle() {}

    protected PassengerVehicle(String type) {
        super(type);
    }

    public int getSeats() {
        return seats;
    }

}
