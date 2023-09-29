package bg.softuni.workshop.models.employee.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeesWrapperDTO {

    @XmlElement(name = "employee")
    private List<ImportEmployeeDTO> employees;

    public ImportEmployeesWrapperDTO() {}

    public List<ImportEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ImportEmployeeDTO> employees) {
        this.employees = employees;
    }

}
