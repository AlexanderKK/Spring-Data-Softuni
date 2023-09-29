package bg.softuni.productsshop.domain.dtos.products.wrappers;

import bg.softuni.productsshop.domain.dtos.products.ProductImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportWrapperDTO {

    @XmlElement(name = "product")
    private List<ProductImportDTO> products;

    public ProductsImportWrapperDTO() {}

    public List<ProductImportDTO> getProducts() {
        return Collections.unmodifiableList(products);
    }

}
