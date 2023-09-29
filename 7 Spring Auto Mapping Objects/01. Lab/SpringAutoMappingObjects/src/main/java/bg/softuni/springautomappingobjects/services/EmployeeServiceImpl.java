package bg.softuni.springautomappingobjects.services;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.Employee;
import bg.softuni.springautomappingobjects.entities.dto2.CreateEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dto2.ReceiveEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dtos.CreateEmployeeDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNameAndSalaryDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNamesDTO;
import bg.softuni.springautomappingobjects.repositories.AddressRepository;
import bg.softuni.springautomappingobjects.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Optional<Address> address = this.addressRepository.findByCountryAndCity(
                employeeDTO.getAddress().getCountry(),
                employeeDTO.getAddress().getCity());

        address.ifPresent(employee::setAddress);

        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return this.employeeRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .toList();
    }

    @Override
    public EmployeeNamesDTO findNamesById(long id) {
        return this.employeeRepository.findNamesById(id);
    }

    @Override
    public EmployeeNameAndSalaryDTO findNamesAndSalaryById(long id) {
        return this.employeeRepository.findFirstNameAndSalaryById(id);
    }

    @Override
    @Transactional
    public List<Employee> create(List<CreateEmployeeDTO2> employees) {
        List<Employee> employeesList = new ArrayList<>();

        for (CreateEmployeeDTO2 employeeDTO : employees) {
            Employee employee = modelMapper.map(employeeDTO, Employee.class);

            Optional<Address> address = this.addressRepository.findByCountryAndCity(
                    employeeDTO.getAddress().getCountry(),
                    employeeDTO.getAddress().getCity());

            address.ifPresent(employee::setAddress);

            Employee manager = this.employeeRepository.findById(1L).orElse(null);
            employee.setManager(manager);

            employeesList.add(employee);
        }

        return this.employeeRepository.saveAll(employeesList);
    }

    @Override
    public List<ReceiveEmployeeDTO2> findAllEmployeesBornBefore(LocalDate date) {
        List<Employee> employees = this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(date)
                .orElseThrow(() -> new RuntimeException("Empty result set of employees"));

        List<ReceiveEmployeeDTO2> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            ReceiveEmployeeDTO2 employeeDTO = modelMapper.map(employee, ReceiveEmployeeDTO2.class);

            employeeDTOs.add(employeeDTO);
        }

        return employeeDTOs;
    }

}
