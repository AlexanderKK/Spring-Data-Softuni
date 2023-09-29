package bg.softuni.springautomappingexercise.services.game;

public interface GameService {

    String addGame(String[] args);
    String editGame(String[] args) throws IllegalAccessException;
    String deleteGame(Long id);

    String getAllGames();

    String getGameDetails(String title);

}
