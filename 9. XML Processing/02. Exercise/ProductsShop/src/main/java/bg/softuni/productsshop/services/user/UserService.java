package bg.softuni.productsshop.services.user;

import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsCountDto;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsDto;
import bg.softuni.productsshop.domain.dtos.users.wrappers.UsersWithSoldProductsCountWrapperDto;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDto> findAllBySellingProductsBuyerNotNull() throws JAXBException;

    List<UserWithSoldProductsCountDto> findAllUsersBySoldProductsCount() throws JAXBException;

}
