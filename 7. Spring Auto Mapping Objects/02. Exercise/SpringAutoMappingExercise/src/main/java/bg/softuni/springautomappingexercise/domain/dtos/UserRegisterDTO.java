package bg.softuni.springautomappingexercise.domain.dtos;

import bg.softuni.springautomappingexercise.domain.entities.User;

import java.util.regex.Pattern;

import static bg.softuni.springautomappingexercise.constants.Validations.*;

public class UserRegisterDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO() {}

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;

        validate();
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    private void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void validate() {
        final boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, this.email);

        if(!isEmailValid) {
            throw new IllegalArgumentException(EMAIL_NOT_VALID_MESSAGE);
        }

        final boolean isPasswordValid = Pattern.matches(PASSWORD_PATTERN, this.password);
        if(!isPasswordValid) {
            throw new IllegalArgumentException(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
        }

        if(!this.password.equals(this.confirmPassword)) {
            throw new IllegalArgumentException(PASSWORD_MISS_MATCH_MESSAGE);
        }
    }

    public User toUser() {
        return new User(email, password, fullName);
    }

    public String successfulRegisterFormat() {
        return fullName + " was registered.";
    }

}
