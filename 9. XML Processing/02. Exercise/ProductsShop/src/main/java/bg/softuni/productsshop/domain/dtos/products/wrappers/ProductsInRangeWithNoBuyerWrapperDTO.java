package bg.softuni.productsshop.domain.dtos.products.wrappers;

import bg.softuni.productsshop.domain.dtos.products.ProductInRangeWithNoBuyerDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeWithNoBuyerWrapperDTO {

    @XmlElement(name = "product")
    private List<ProductInRangeWithNoBuyerDTO> products;

    public ProductsInRangeWithNoBuyerWrapperDTO() {}

    public ProductsInRangeWithNoBuyerWrapperDTO(List<ProductInRangeWithNoBuyerDTO> products) {
        this.products = products;
    }

    public List<ProductInRangeWithNoBuyerDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInRangeWithNoBuyerDTO> products) {
        this.products = products;
    }

}
