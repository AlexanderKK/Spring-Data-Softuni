package bg.softuni.springautomappingexercise;

import bg.softuni.springautomappingexercise.services.game.GameService;
import bg.softuni.springautomappingexercise.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static bg.softuni.springautomappingexercise.constants.Commands.*;
import static bg.softuni.springautomappingexercise.constants.Validations.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private GameService gameService;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        String input;
        while(!(input = scanner.nextLine()).equalsIgnoreCase("close")) {
            final String[] inputArr = input.split("\\|");

            final String command = inputArr[0];

            String output = switch (command) {
                case REGISTER_USER -> userService.registerUser(inputArr);
                case LOGIN_USER -> userService.loginUser(inputArr);
                case LOGOUT_USER -> userService.logoutUser();
                case ADD_GAME -> gameService.addGame(inputArr);
                case EDIT_GAME -> gameService.editGame(inputArr);
                case DELETE_GAME -> gameService.deleteGame(Long.parseLong(inputArr[1]));
                case ALL_GAMES -> gameService.getAllGames();
                case DETAIL_GAME -> gameService.getGameDetails(inputArr[1]);
                default -> COMMAND_NOT_FOUND_MESSAGE;
            };

            System.out.println(output);
        }
    }

}
