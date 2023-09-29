package bg.softuni.productsshop.domain.dtos.users.wrappers;

import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsXmlDto;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsWrapperDto {

    @XmlElement(name = "user")
    private List<UserWithSoldProductsXmlDto> users;

    public UsersWithSoldProductsWrapperDto() {}

    public UsersWithSoldProductsWrapperDto(List<UserWithSoldProductsXmlDto> users) {
        this.users = users;
    }

    public List<UserWithSoldProductsXmlDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsXmlDto> users) {
        this.users = users;
    }

    public UsersWithSoldProductsWrapperDto ofListOfUsersWithSoldProductsDto(List<UserWithSoldProductsDto> input) {
        this.users = UserWithSoldProductsDto.toUsersWithSoldProductsXmlDto(input);

        return this;
    }

}
