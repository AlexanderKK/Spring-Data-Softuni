package bg.softuni.springdataintro.services;

import bg.softuni.springdataintro.models.User;

public interface UserService {

    void register(String username, int age);

    User findByUsername(String username);

}
