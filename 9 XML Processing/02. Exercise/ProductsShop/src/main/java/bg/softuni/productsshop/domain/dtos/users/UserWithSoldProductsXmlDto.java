package bg.softuni.productsshop.domain.dtos.users;

import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldWrapperDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsXmlDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapperDto soldProducts;

    public UserWithSoldProductsXmlDto() {}

    public UserWithSoldProductsXmlDto(String firstName, String lastName, ProductsSoldWrapperDto soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProductsSoldWrapperDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsSoldWrapperDto soldProducts) {
        this.soldProducts = soldProducts;
    }

}
