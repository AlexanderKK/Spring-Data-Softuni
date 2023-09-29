package bg.softuni.productsshop.domain.dtos.users;

import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldCountWrapperDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsCountXmlDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private int age;

    @XmlElement(name = "sold-products")
    private ProductsSoldCountWrapperDto soldProducts;

    public UserWithSoldProductsCountXmlDto() {}

    public UserWithSoldProductsCountXmlDto(
            String firstName, String lastName, int age, ProductsSoldCountWrapperDto soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductsSoldCountWrapperDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsSoldCountWrapperDto soldProducts) {
        this.soldProducts = soldProducts;
    }

}
