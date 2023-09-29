package bg.softuni.workshop.models.employee.dto;

import bg.softuni.workshop.models.project.dto.ImportProjectDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeeDTO {

    @NotBlank
    @XmlElement(name = "first-name")
    private String firstName;

    @NotBlank
    @XmlElement(name = "last-name")
    private String lastName;

    @NotNull
    @XmlElement
    private Integer age;

    @NotNull
    @XmlElement
    private ImportProjectDTO project;

    public ImportEmployeeDTO() {}

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ImportProjectDTO getProject() {
        return project;
    }

    public void setProject(ImportProjectDTO project) {
        this.project = project;
    }

}
