package bg.softuni.springautomappingexercise.services.user;

import bg.softuni.springautomappingexercise.domain.entities.User;
import bg.softuni.springautomappingexercise.domain.dtos.UserLoginDTO;
import bg.softuni.springautomappingexercise.domain.dtos.UserRegisterDTO;
import bg.softuni.springautomappingexercise.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bg.softuni.springautomappingexercise.constants.Validations.USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    private User loggedInUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {
        final String email = args[1];
        final String password = args[2];
        final String confirmPassword = args[3];
        final String fullName = args[4];

        final UserRegisterDTO userRegisterDTO;
        try {
            userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        final User user = this.modelMapper.map(userRegisterDTO, User.class);
//        final User user = userRegisterDTO.toUser(); //UserDTO -> User

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        }

        boolean isUserFound = this.userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();

        if(isUserFound) {
//            throw new IllegalArgumentException("Email already exists");
            return "Email already exists";
        }

        this.userRepository.save(user);

        return userRegisterDTO.successfulRegisterFormat();
    }

    @Override
    public String loginUser(String[] args) {
        final String email = args[1];
        final String password = args[2];

        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        Optional<User> user = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if(user.isPresent() &&
                this.loggedInUser == null &&
                userLoginDTO.getPassword().equals(user.get().getPassword())) {
            this.loggedInUser = this.userRepository.findByEmail(userLoginDTO.getEmail()).get();

            return "Successfully logged in " + this.loggedInUser.getFullName();
        }

        return USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE;
    }

    @Override
    public String logoutUser() {
        if(this.loggedInUser == null) {
            return "Cannot log out. No user was logged in.";
        }

        String userFullName = loggedInUser.getFullName();

        this.loggedInUser = null;

        return String.format("User %s successfully logged out.", userFullName);
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

}
