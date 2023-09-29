package bg.softuni.workshop.models.project.dto;

import java.math.BigDecimal;

public class ExportFinishedProjectDTO {

    private String name;
    private String description;
    private BigDecimal payment;

    public ExportFinishedProjectDTO() {}

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

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Project name: %s", name)).append(System.lineSeparator());
        sb.append(String.format("\tDescription: %s", description)).append(System.lineSeparator());
        sb.append(String.format("\tPayment: %.2f", payment));

        return sb.toString();
    }

}
