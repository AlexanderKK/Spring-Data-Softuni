package bg.softuni.productsshop.domain.dtos.users.wrappers;

import bg.softuni.productsshop.domain.dtos.users.UserImportDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportWrapperDTO {

    @XmlElement(name = "user")
    private List<UserImportDTO> users;

    public UsersImportWrapperDTO() {}

    public List<UserImportDTO> getUsers() {
        return users;
    }

}
