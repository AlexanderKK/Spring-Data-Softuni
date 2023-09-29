package bg.softuni.workshop.controllers;

import bg.softuni.workshop.services.CompanyService;
import bg.softuni.workshop.services.EmployeeService;
import bg.softuni.workshop.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportXMLController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportXMLController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        Boolean areCompaniesImported = this.companyService.areImported();
        Boolean areProjectsImported = this.projectService.areImported();
        Boolean areEmployeesImported = this.employeeService.areImported();

        Boolean[] importStatuses =
                { areCompaniesImported, areProjectsImported, areEmployeesImported };

        model.addAttribute("areImported", importStatuses);

        return "xml/import-xml";
    }

    /**
     * <b><i>Companies<i/></b>
     */
    @GetMapping("/import/companies")
    public String loadCompanies(Model model) throws IOException {
        String companiesXML = this.companyService.getXMLContent();

        model.addAttribute("companies", companiesXML);

        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies() throws IOException, JAXBException {
        this.companyService.importCompanies();

        return "redirect:/import/xml";
    }

    /**
     * <b><i>Projects<i/></b>
     */
    @GetMapping("/import/projects")
    public String loadProjects(Model model) throws IOException {
        String projectsXML = this.projectService.getXMLContent();
        model.addAttribute("projects", projectsXML);

        return "xml/import-projects";
    }

    @PostMapping("/import/projects")
    public String importProjects() throws IOException, JAXBException {
        this.projectService.importProjects();

        return "redirect:/import/xml";
    }

    /**
     * <b><i>Employees<i/></b>
     */
    @GetMapping("/import/employees")
    public String loadEmployees(Model model) throws IOException {
        String employeesXML = this.employeeService.getXMLContent();
        model.addAttribute("employees", employeesXML);

        return "xml/import-employees";
    }

    @PostMapping("/import/employees")
    public String importEmployees() throws IOException, JAXBException {
        this.employeeService.importEmployees();

        return "redirect:/import/xml";
    }

}
