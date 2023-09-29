package bg.softuni.productsshop.domain.dtos.users;

import bg.softuni.productsshop.domain.dtos.products.ProductSoldDto;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldWrapperDto;

import java.util.List;

public class UserWithSoldProductsDto {

    private String firstName;

    private String lastName;

    private List<ProductSoldDto> soldProducts;

    public UserWithSoldProductsDto() {}

    public UserWithSoldProductsDto(String firstName, String lastName, List<ProductSoldDto> soldProducts) {
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

    public List<ProductSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public static List<UserWithSoldProductsXmlDto> toUsersWithSoldProductsXmlDto(List<UserWithSoldProductsDto> input) {
        return input.stream()
                .map(user -> new UserWithSoldProductsXmlDto(
                        user.getFirstName(),
                        user.getLastName(),
                        new ProductsSoldWrapperDto(user.getSoldProducts())))
                .toList();
    }

}
