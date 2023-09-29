package bg.softuni.springautomappingobjects.entities.dtos;

import bg.softuni.springautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEmployeeDTO {

    @Expose
    private final String firstName;

    private final String lastname;

    @Expose
    private final BigDecimal salary;

    @Expose
    private final LocalDate birthday;

    @Expose
    private final CreateAddressDTO address;

    public CreateEmployeeDTO(String firstName, String lastname, BigDecimal salary, LocalDate birthday, CreateAddressDTO address) {

        this.firstName = firstName;
        this.lastname = lastname;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public CreateAddressDTO getAddress() {
        return address;
    }

}
