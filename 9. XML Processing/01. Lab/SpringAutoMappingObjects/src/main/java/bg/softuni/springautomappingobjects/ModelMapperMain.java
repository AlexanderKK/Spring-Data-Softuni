package bg.softuni.springautomappingobjects;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.Employee;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper =  new ModelMapper();
//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//            }
//        };

//        mapper.addMappings(propertyMap);

        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);

        typeMap.addMappings(mapping -> mapping.map(source ->
                source.getAddress().getCity(),
                EmployeeDTO::setAddressCity)
        );
        typeMap.validate();

        Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("First", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = typeMap.map(employee);

//        System.out.println(employeeDTO);
    }

}
