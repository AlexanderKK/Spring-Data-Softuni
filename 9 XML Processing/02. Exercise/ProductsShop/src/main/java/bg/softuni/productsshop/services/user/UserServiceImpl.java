package bg.softuni.productsshop.services.user;

import bg.softuni.productsshop.common.Utils;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsCountDto;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsCountXmlDto;
import bg.softuni.productsshop.domain.dtos.users.UserWithSoldProductsDto;
import bg.softuni.productsshop.domain.dtos.users.wrappers.UsersWithSoldProductsCountWrapperDto;
import bg.softuni.productsshop.domain.dtos.users.wrappers.UsersWithSoldProductsWrapperDto;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.productsshop.common.ConstantPaths.OUTPUT_USERS_AND_PRODUCTS_COUNT_XML_PATH;
import static bg.softuni.productsshop.common.ConstantPaths.OUTPUT_USERS_WITH_SOLD_PRODUCTS_XML_PATH;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public List<UserWithSoldProductsDto> findAllBySellingProductsBuyerNotNull() throws JAXBException {
        final List<UserWithSoldProductsDto> userWithSoldProductsDtoList = this.userRepository
                .findAllBySoldProductsNotNullOrderBySoldProductsBuyerLastNameAscSoldProductsBuyerFirstNameAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserWithSoldProductsDto.class))
                .toList();

        final UsersWithSoldProductsWrapperDto usersWithSoldProductsWrapperDto =
                new UsersWithSoldProductsWrapperDto().ofListOfUsersWithSoldProductsDto(userWithSoldProductsDtoList);

        Utils.writeXmlIntoFile(usersWithSoldProductsWrapperDto, OUTPUT_USERS_WITH_SOLD_PRODUCTS_XML_PATH);

        return userWithSoldProductsDtoList;
    }

    @Override
    public List<UserWithSoldProductsCountDto> findAllUsersBySoldProductsCount() throws JAXBException {
        List<UserWithSoldProductsCountDto> usersDto = this.userRepository
                .findAllBySoldProductsIsNotNull()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserWithSoldProductsCountDto.class))
                .sorted()
                .toList();

        List<UserWithSoldProductsCountXmlDto> usersXmlDto = usersDto.stream()
                .map(userWithSoldProductsCountDto ->
                        userWithSoldProductsCountDto.toUserWithSoldProductsCountXmlDto(userWithSoldProductsCountDto))
                .toList();

        UsersWithSoldProductsCountWrapperDto usersWithSoldProductsCountWrapperDto =
                new UsersWithSoldProductsCountWrapperDto(usersXmlDto);

        Utils.writeXmlIntoFile(usersWithSoldProductsCountWrapperDto, OUTPUT_USERS_AND_PRODUCTS_COUNT_XML_PATH);

        return usersDto;
    }

}
