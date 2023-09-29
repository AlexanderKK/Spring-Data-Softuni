package bg.softuni.springautomappingexercise.constants;

import bg.softuni.springautomappingexercise.domain.dtos.GameDTO;

import java.math.BigDecimal;

public enum Validations {

    ;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)+$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    public static final String EMAIL_NOT_VALID_MESSAGE = "Incorrect email.";
    public static final String USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE = "Incorrect username / password.";
    public static final String PASSWORD_MISS_MATCH_MESSAGE = "Passwords are not matching.";
    public static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found.";
    public static final String IMPOSSIBLE_OPERATION = "Impossible operation";
    public static final String USER_NOT_LOGGED = "User not logged in";

}
