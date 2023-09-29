package bg.softuni.workshop.controllers;

import bg.softuni.workshop.services.CompanyService;
import bg.softuni.workshop.services.EmployeeService;
import bg.softuni.workshop.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public HomeController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Boolean isDatabaseSeeded =
                companyService.areImported() && projectService.areImported() && employeeService.areImported();

        model.addAttribute("areImported", isDatabaseSeeded);

        return "home";
    }

}
