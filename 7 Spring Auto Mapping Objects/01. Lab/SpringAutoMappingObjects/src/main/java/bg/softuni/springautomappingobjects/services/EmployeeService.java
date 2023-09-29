package bg.softuni.springautomappingobjects.services;

import bg.softuni.springautomappingobjects.entities.Employee;
import bg.softuni.springautomappingobjects.entities.dto2.CreateEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dto2.ReceiveEmployeeDTO2;
import bg.softuni.springautomappingobjects.entities.dtos.CreateEmployeeDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNameAndSalaryDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNamesDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(long id);

    EmployeeNameAndSalaryDTO findNamesAndSalaryById(long id);

    List<Employee> create(List<CreateEmployeeDTO2> employees);

    List<ReceiveEmployeeDTO2> findAllEmployeesBornBefore(LocalDate date);

}
