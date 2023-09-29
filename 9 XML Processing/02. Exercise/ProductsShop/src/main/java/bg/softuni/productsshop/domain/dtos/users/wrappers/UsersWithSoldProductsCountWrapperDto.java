package bg.softuni.productsshop.domain.dtos.users.wrappers;

import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsCountXmlDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsCountWrapperDto {

    @XmlAttribute(name = "count")
    private Long usersCount;

    @XmlElement(name = "user")
    private List<UserWithSoldProductsCountXmlDto> users;

    public UsersWithSoldProductsCountWrapperDto() {}

    public UsersWithSoldProductsCountWrapperDto(List<UserWithSoldProductsCountXmlDto> users) {
        this.users = users;
        this.usersCount = (long) users.size();
    }

    public Long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Long usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithSoldProductsCountXmlDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductsCountXmlDto> users) {
        this.users = users;
    }

}
