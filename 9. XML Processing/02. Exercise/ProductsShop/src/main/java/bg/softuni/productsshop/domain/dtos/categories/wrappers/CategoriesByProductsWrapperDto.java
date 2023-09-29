package bg.softuni.productsshop.domain.dtos.categories.wrappers;

import bg.softuni.productsshop.domain.dtos.categories.CategoryByProductsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsWrapperDto {

    @XmlElement(name = "category")
    private List<CategoryByProductsDto> categories;

    public CategoriesByProductsWrapperDto() {}

    public CategoriesByProductsWrapperDto(List<CategoryByProductsDto> categories) {
        this.categories = categories;
    }

    public List<CategoryByProductsDto> getCategories() {
        return categories;
    }

}
