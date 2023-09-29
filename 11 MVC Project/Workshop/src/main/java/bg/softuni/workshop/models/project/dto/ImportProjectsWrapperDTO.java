package bg.softuni.workshop.models.project.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectsWrapperDTO {

    @XmlElement(name = "project")
    private List<ImportProjectDTO> projects;

    public ImportProjectsWrapperDTO() {}

    public List<ImportProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ImportProjectDTO> projects) {
        this.projects = projects;
    }

}
