package bg.softuni.productsshop.domain.dtos.products.wrappers;

import bg.softuni.productsshop.domain.dtos.products.ProductSoldDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWrapperDto {

    @XmlElement(name = "product")
    private List<ProductSoldDto> soldProducts;

    public ProductsSoldWrapperDto() {}

    public ProductsSoldWrapperDto(List<ProductSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public List<ProductSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

}
