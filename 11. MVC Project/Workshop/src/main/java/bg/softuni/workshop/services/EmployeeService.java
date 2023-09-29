package bg.softuni.workshop.services;

import bg.softuni.workshop.models.employee.Employee;
import bg.softuni.workshop.models.employee.dto.ExportEmployeeOverAgeDTO;
import bg.softuni.workshop.models.employee.dto.ImportEmployeesWrapperDTO;
import bg.softuni.workshop.repositories.EmployeeRepository;
import bg.softuni.workshop.repositories.ProjectRepository;
import bg.softuni.workshop.utils.ValidationUtil;
import bg.softuni.workshop.utils.XmlParser;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.workshop.constants.FilePaths.IMPORT_EMPLOYEES_XML_PATH;

@Service
public class EmployeeService {

    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public EmployeeService(ValidationUtil validationUtil,
                           XmlParser xmlParser,
                           ModelMapper mapper,
                           EmployeeRepository employeeRepository,
                           ProjectRepository projectRepository) {
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(IMPORT_EMPLOYEES_XML_PATH);
    }

    @Transactional
    public void importEmployees() throws JAXBException, IOException {
        ImportEmployeesWrapperDTO employeesDTO =
                xmlParser.fromFile(IMPORT_EMPLOYEES_XML_PATH.toFile(), ImportEmployeesWrapperDTO.class);

        List<Employee> employees = employeesDTO.getEmployees().stream()
                .filter(employeeDTO -> {
                    boolean isValid =
                            this.validationUtil.isValid(employeeDTO) &&
                            this.projectRepository.findFirstByName(employeeDTO.getProject().getName()).isPresent();

                    System.out.println(isValid
                            ? String.format("Successfully imported employee %s %s",
                                employeeDTO.getFirstName(), employeeDTO.getLastName())
                            : "Invalid employee");

                    return isValid;
                })
                .map(employeeDTO -> mapper.map(employeeDTO, Employee.class))
                .map(employee -> employee.buildForProject(
                        this.projectRepository.findFirstByName(employee.getProject().getName()).get()))
                .toList();

        this.employeeRepository.saveAll(employees);

//        employeesDTO.getEmployees()
//                .forEach(employeeDTO -> {
//                    Optional<Project> project = this.projectRepository.findFirstByName(employeeDTO.getProject().getName());
//                    if(project.isPresent()) {
//                        Employee employeeToSave = mapper.map(employeeDTO, Employee.class);
//                        employeeToSave.setProject(project.get());
//
//                        this.employeeRepository.saveAndFlush(employeeToSave);
//                    }
//                });
    }

    public Boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    public String getEmployeesAboveAge(Integer age) {
        List<Employee> employees = this.employeeRepository.findAllByAgeGreaterThan(age);

        if(employees.size() == 0) {
            return "No employees found";
        }

        return employees.stream()
                .map(employee -> mapper.map(employee, ExportEmployeeOverAgeDTO.class))
                .map(ExportEmployeeOverAgeDTO::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        // 32 -> 100000 -> 1 * 2^5 + 0 * 2^4 + 0 * 2^3 + 0 * 2^2 + 0 * 2^1 + 0 * 2^0
    }

}
