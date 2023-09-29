package bg.softuni.productsshop.domain.dtos.categories;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement
    private String name;

    public CategoryImportDTO() {}

    public CategoryImportDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
