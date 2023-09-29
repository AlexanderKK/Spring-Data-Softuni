package bg.softuni.productsshop.domain.dtos.categories.wrappers;

import bg.softuni.productsshop.domain.dtos.categories.CategoryImportDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportWrapperDTO {

    @XmlElement(name = "category")
    private List<CategoryImportDTO> categories;

    public CategoriesImportWrapperDTO() {}

    public List<CategoryImportDTO> getCategories() {
        return categories;
    }

}
