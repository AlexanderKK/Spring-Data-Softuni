package bg.softuni.springautomappingexercise.services.user;

import bg.softuni.springautomappingexercise.domain.entities.User;

public interface UserService {

    String registerUser(String[] args);

    String loginUser(String[] args);

    String logoutUser();

    User getLoggedInUser();

}
