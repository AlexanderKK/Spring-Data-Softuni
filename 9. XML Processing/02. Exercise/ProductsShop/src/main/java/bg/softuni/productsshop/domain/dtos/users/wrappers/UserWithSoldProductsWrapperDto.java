package bg.softuni.productsshop.domain.dtos.users.wrappers;

import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldWrapperDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsWrapperDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapperDto boughtProducts;

    public UserWithSoldProductsWrapperDto() {}

    public UserWithSoldProductsWrapperDto(String firstName, String lastName, ProductsSoldWrapperDto boughtProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtProducts = boughtProducts;
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

    public ProductsSoldWrapperDto getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ProductsSoldWrapperDto boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

}
