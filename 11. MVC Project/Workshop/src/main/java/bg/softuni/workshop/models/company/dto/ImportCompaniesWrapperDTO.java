package bg.softuni.workshop.models.company.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompaniesWrapperDTO {

    @XmlElement(name = "company")
    private List<ImportCompanyDTO> companies;

    public ImportCompaniesWrapperDTO() {}

    public List<ImportCompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<ImportCompanyDTO> companies) {
        this.companies = companies;
    }

}
