package bg.softuni.workshop.models.employee.dto;

public class ExportEmployeeOverAgeDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private String projectName;

    public ExportEmployeeOverAgeDTO() {}

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s %s", firstName, lastName)).append(System.lineSeparator());
        sb.append(String.format("\tAge: %d", age)).append(System.lineSeparator());
        sb.append(String.format("\tProject name: %s", projectName));

        return sb.toString();
    }

}
