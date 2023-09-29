package bg.softuni.productsshop.services.user;

import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
