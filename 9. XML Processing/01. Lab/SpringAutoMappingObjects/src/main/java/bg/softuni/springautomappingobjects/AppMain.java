package bg.softuni.springautomappingobjects;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.Employee;
import bg.softuni.springautomappingobjects.entities.dto2.CreateEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dto2.ReceiveEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dtos.*;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.springautomappingobjects.services.AddressService;
import bg.softuni.springautomappingobjects.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@Component
public class AppMain implements CommandLineRunner {

    private final AddressService addressService;
    private final EmployeeService employeeService;
    private final Scanner scanner;
    private final Gson gson;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        createAddress();
//        createEmployee();
//        printAllEmployees();
//        printEmployeeNames(1L);
//        printEmployeeNameAndSalary();
//        createEmployees();
//        printAllEmployeesBornBefore();
    }

    private void printAllEmployeesBornBefore() {
        LocalDate date = LocalDate.parse(scanner.nextLine());

        List<ReceiveEmployeeDTO2> receiveEmployeeDTO2 =
                this.employeeService.findAllEmployeesBornBefore(date);

        receiveEmployeeDTO2.forEach(System.out::println);
    }

    private void createEmployees() {
        //first name, last name, salary, birthday, address and manager

        List<CreateEmployeeDTO2> employees = new ArrayList<>();
        String input;
        while(!(input = scanner.nextLine()).isEmpty()) {
            String[] inputArr = input.split("\\s+");

            String firstName = inputArr[0];
            String lastName = inputArr[1];
            BigDecimal salary = new BigDecimal(inputArr[2]);
            LocalDate birthday = LocalDate.parse(inputArr[3]);
            Address address = new Address(inputArr[4], inputArr[5]);
            Employee manager = new Employee();

            CreateEmployeeDTO2 employeeDTO = new CreateEmployeeDTO2(firstName, lastName, salary, birthday, address, manager);
            employees.add(employeeDTO);
        }

        List<Employee> employeesPersisted = this.employeeService.create(employees);

        employeesPersisted.forEach(System.out::println);
    }

    private void printEmployeeNameAndSalary() {
        EmployeeNameAndSalaryDTO namesAndSalaryById = this.employeeService.findNamesAndSalaryById(1L);

        System.out.println(namesAndSalaryById.getFirstName() + " " + namesAndSalaryById.getSalary());
    }

    private void printEmployeeNames(long employeeId) {
        EmployeeNamesDTO namesById = this.employeeService.findNamesById(employeeId);
        System.out.println(namesById);
    }

    private void printAllEmployees() {
        this.employeeService.findAll().forEach(System.out::println);
    }

    private void createEmployee() {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO address = new CreateAddressDTO(country, city);

        CreateEmployeeDTO employeeDTO =
                new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(employeeDTO);

        System.out.println(employee);
//        long addressId = Long.parseLong(scanner.nextLine());
    }

    private void createAddress() {
//        String country = scanner.nextLine();
//        String city = scanner.nextLine();

        String input = this.scanner.nextLine();

        CreateAddressDTO createAddressDTO = this.gson.fromJson(input, CreateAddressDTO.class);

//        AddressDTO data = new AddressDTO(country, city);

        AddressDTO createdAddress = addressService.create(createAddressDTO);

        System.out.println(this.gson.toJson(createdAddress));

        System.out.println();
    }

}
