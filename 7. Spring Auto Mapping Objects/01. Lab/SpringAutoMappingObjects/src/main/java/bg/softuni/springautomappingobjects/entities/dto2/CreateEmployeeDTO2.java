package bg.softuni.springautomappingobjects.entities.dto2;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEmployeeDTO2 {

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private Address address;
    private Employee manager;

    public CreateEmployeeDTO2() {}

    public CreateEmployeeDTO2(String firstName,
                              String lastName,
                              BigDecimal salary,
                              LocalDate birthday,
                              Address address,
                              Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.manager = manager;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
