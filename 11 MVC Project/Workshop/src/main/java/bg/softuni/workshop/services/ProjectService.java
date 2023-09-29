package bg.softuni.workshop.services;

import bg.softuni.workshop.models.project.Project;
import bg.softuni.workshop.models.project.dto.ExportFinishedProjectDTO;
import bg.softuni.workshop.models.project.dto.ImportProjectsWrapperDTO;
import bg.softuni.workshop.repositories.CompanyRepository;
import bg.softuni.workshop.repositories.ProjectRepository;
import bg.softuni.workshop.utils.ValidationUtil;
import bg.softuni.workshop.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.workshop.constants.FilePaths.IMPORT_PROJECTS_XML_PATH;

@Service
public class ProjectService {

    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ProjectService(ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper mapper, ProjectRepository projectRepository, CompanyRepository companyRepository) {
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(IMPORT_PROJECTS_XML_PATH);
    }

    public void importProjects() throws IOException, JAXBException {
        ImportProjectsWrapperDTO projectsDTO =
                xmlParser.fromFile(IMPORT_PROJECTS_XML_PATH.toFile(), ImportProjectsWrapperDTO.class);

        List<Project> projects = projectsDTO.getProjects().stream()
                .filter(projectDTO -> {
                    boolean isValid =
                            this.validationUtil.isValid(projectDTO) &&
                            this.companyRepository.findByName(projectDTO.getCompany().getName()).isPresent();

                    System.out.println(isValid
                            ? String.format("Successfully imported project %s", projectDTO.getName())
                            : "Invalid project");

                    return isValid;
                })
                .map(projectDTO -> this.mapper.map(projectDTO, Project.class))
                .map(project -> project.buildForCompany(
                        this.companyRepository.findByName(project.getCompany().getName()).get()))
                .toList();

        this.projectRepository.saveAll(projects);

//        projectsDTO.getProjects()
//                .forEach(projectDTO -> {
//                    Optional<Company> company = this.companyRepository.findByName(projectDTO.getCompany().getName());
//                    if(company.isPresent()) {
//                        Project projectToSave = mapper.map(projectDTO, Project.class);
//                        projectToSave.setCompany(company.get());
//
//                        this.projectRepository.saveAndFlush(projectToSave);
//                    }
//                });
    }

    public Boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    public String getFinishedProjects() {
        List<Project> finishedProjects = this.projectRepository.findAllByIsFinishedIsTrue();

        if(finishedProjects.size() == 0) {
            return "No projects found";
        }

        return finishedProjects.stream()
                .map(project -> mapper.map(project, ExportFinishedProjectDTO.class))
                .map(ExportFinishedProjectDTO::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
