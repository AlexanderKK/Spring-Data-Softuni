package bg.softuni.productsshop.domain.dtos.products.wrappers;

import bg.softuni.productsshop.domain.dtos.products.ProductSoldCountDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldCountWrapperDto {

    @XmlAttribute(name = "count")
    private Long productsCount;

    @XmlElement(name = "product")
    private List<ProductSoldCountDto> soldProducts;

    public ProductsSoldCountWrapperDto() {}

    public ProductsSoldCountWrapperDto(List<ProductSoldCountDto> soldProducts) {
        this.soldProducts = soldProducts;
        this.productsCount = (long) soldProducts.size();
    }

    public Long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }

    public List<ProductSoldCountDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldCountDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

}
