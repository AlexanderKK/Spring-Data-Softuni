package bg.softuni.productsshop.domain.dtos.users;

import bg.softuni.productsshop.domain.dtos.products.ProductSoldCountDto;
import bg.softuni.productsshop.domain.dtos.products.ProductSoldDto;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldCountWrapperDto;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsSoldWrapperDto;

import java.util.List;

public class UserWithSoldProductsCountDto implements Comparable<UserWithSoldProductsCountDto> {

    private String firstName;

    private String lastName;

    private int age;

    private List<ProductSoldCountDto> soldProducts;

    public UserWithSoldProductsCountDto() {}

    public UserWithSoldProductsCountDto(String firstName, String lastName, int age, List<ProductSoldCountDto> soldProducts) {
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

    public List<ProductSoldCountDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldCountDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public UserWithSoldProductsCountXmlDto toUserWithSoldProductsCountXmlDto(UserWithSoldProductsCountDto userDto) {
        return new UserWithSoldProductsCountXmlDto(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAge(),
                new ProductsSoldCountWrapperDto(userDto.getSoldProducts()));
    }

    @Override
    public int compareTo(UserWithSoldProductsCountDto other) {
        int result = Integer.compare(other.soldProducts.size(), this.soldProducts.size());

        if(result == 0) {
            result = this.lastName.compareTo(other.lastName);
        }

        return result;
    }

}
