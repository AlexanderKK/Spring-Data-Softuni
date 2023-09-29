package bg.softuni.springdataintro.services;

import bg.softuni.springdataintro.models.Account;
import bg.softuni.springdataintro.models.User;
import bg.softuni.springdataintro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {
        // Validate username + age
        if(username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        // Check username is unique
        Optional<User> byUsername = this.userRepository.findByUsername(username);
        if(byUsername.isPresent()) {
            throw new RuntimeException("Username already in use");
        }

//        if(byUsername != null) {
//            throw new RuntimeException("Username already in use");
//        }

        // Add default account
        Account account = new Account();
        User user = new User(username, age, account);

        // Save user
        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }

}
