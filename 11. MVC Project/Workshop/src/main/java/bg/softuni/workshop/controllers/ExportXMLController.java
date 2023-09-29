package bg.softuni.workshop.controllers;

import bg.softuni.workshop.services.EmployeeService;
import bg.softuni.workshop.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportXMLController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ExportXMLController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/export/project-if-finished")
    public String exportFinishedProjects(Model model) {
        String finishedProjects = this.projectService.getFinishedProjects();

        model.addAttribute("projectsIfFinished", finishedProjects);

        return "export/export-project-if-finished";
    }

    @GetMapping("/export/employees-above")
    public String exportEmployeesAboveAge(Model model) {
        String employeesAboveAge = this.employeeService.getEmployeesAboveAge(25);

        model.addAttribute("employeesAbove", employeesAboveAge);

        return "export/export-employees-with-age";
    }

}
