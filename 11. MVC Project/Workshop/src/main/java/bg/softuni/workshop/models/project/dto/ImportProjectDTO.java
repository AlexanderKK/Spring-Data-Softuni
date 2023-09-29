package bg.softuni.workshop.models.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectDTO {

    @NotBlank
    @XmlElement
    private String name;

    @NotBlank
    @XmlElement
    private String description;

    @XmlElement(name = "start-date")
    private String startDate;

    @XmlElement(name = "is-finished")
    private Boolean isFinished;

    @NotNull
    @XmlElement
    private BigDecimal payment;

    @NotNull
    @XmlElement
    private ProjectCompanyWrapperDTO company;

    public ImportProjectDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public @NotNull ProjectCompanyWrapperDTO getCompany() {
        return company;
    }

    public void setCompany(@NotNull ProjectCompanyWrapperDTO company) {
        this.company = company;
    }

}
