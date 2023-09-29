package bg.softuni.springautomappingexercise.services.game;

import bg.softuni.springautomappingexercise.domain.dtos.GameDTO;
import bg.softuni.springautomappingexercise.domain.entities.Game;
import bg.softuni.springautomappingexercise.repositories.GameRepository;
import bg.softuni.springautomappingexercise.services.user.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static bg.softuni.springautomappingexercise.constants.Validations.IMPOSSIBLE_OPERATION;
import static bg.softuni.springautomappingexercise.constants.Validations.USER_NOT_LOGGED;

@Service
public class GameServiceImpl implements GameService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final ModelMapper modelMapper = new ModelMapper();
    private final GameRepository gameRepository;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] args) {
        if(this.userService.getLoggedInUser() == null) {
            return USER_NOT_LOGGED;
        }

        if(!this.userService.getLoggedInUser().getIsAdmin()) {
            return IMPOSSIBLE_OPERATION;
        }

        final String title = args[1];
        final BigDecimal price = new BigDecimal(args[2]);
        final Float size = Float.parseFloat(args[3]);
        final String trailer = args[4];
        final String imageUrl = args[5];
        final String description = args[6];

        final LocalDate releaseDate = LocalDate.parse(args[7], dateTimeFormatter);

        final GameDTO gameDTO;

        try {
            gameDTO = new GameDTO(title, trailer, imageUrl, size, price, description, releaseDate);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        final Game game = this.modelMapper.map(gameDTO, Game.class);

        this.gameRepository.save(game);

        return "Added " + game.getTitle();
    }

    @Override
    @Transactional
    public String editGame(String[] args) throws IllegalAccessException {
        if(this.userService.getLoggedInUser() == null) {
            return USER_NOT_LOGGED;
        }

        if(!this.userService.getLoggedInUser().getIsAdmin()) {
            return IMPOSSIBLE_OPERATION;
        }

        Long gameId = Long.parseLong(args[1]);

        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        if(optionalGame.isEmpty()) {
            return "Game not present";
        }

        Game game = optionalGame.get();
        GameDTO gameDTO = game.toDTO();
        String oldTitle = gameDTO.getTitle();

        String[] keyValuePairs = Arrays.stream(args).skip(2).toArray(String[]::new);

        GameDTO.Builder gameDTOBuilder = GameDTO.builder();
        for (String keyValuePair : keyValuePairs) {
            String[] pair = keyValuePair.split("=");

            String key = pair[0];
            String value = pair[1];

            switch(key) {
                case "title" -> gameDTO.setTitle(value);
                case "price" -> gameDTO.setPrice(new BigDecimal(value));
                case "size" -> gameDTO.setSize(Float.parseFloat(value));
                case "trailer" -> gameDTO.setTrailer(value);
                case "imageUrl" -> gameDTO.setImageUrl(value);
                case "description" -> gameDTO.setDescription(value);
                case "releaseDate" -> gameDTO.setReleaseDate(LocalDate.parse(value, dateTimeFormatter));
            }
        }

//        GameDTO gameDTO = gameDTOBuilder.build();

        game = modelMapper.map(gameDTO, Game.class);

        this.gameRepository.updateGamesById(gameId, game.getTitle(), game.getTrailer(), game.getPrice(),
                game.getSize(), game.getImageUrl(), game.getDescription(), game.getReleaseDate());

        return "Edited " + oldTitle;
    }

    @Override
    public String deleteGame(Long id) {
        if(this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().getIsAdmin()) {
            Optional<Game> game = this.gameRepository.findById(id);

            if(game.isPresent()) {
                this.gameRepository.deleteById(id);

                return "Deleted " + game.get().getTitle();
            }
        }

        return IMPOSSIBLE_OPERATION;
    }

    @Override
    public String getAllGames() {
        List<Game> games = this.gameRepository.findAll();

        if(games.isEmpty()) {
            return "No games currently in the store.";
        }

        StringBuilder sb = new StringBuilder();

        games.forEach(g -> {
            sb.append(String.format("%s %.2f", g.getTitle(), g.getPrice()));
            sb.append(System.lineSeparator());
        });

        return sb.toString().trim();
    }

    @Override
    public String getGameDetails(String title) {
        Optional<Game> firstByTitle = this.gameRepository.findFirstByTitle(title);

        if(firstByTitle.isEmpty()) {
            return "Game not present in store.";
        }

        Game game = firstByTitle.get();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Title: %s", game.getTitle())).append(System.lineSeparator());
        sb.append(String.format("Price: %.2f", game.getPrice())).append(System.lineSeparator());
        sb.append(String.format("Description: %s", game.getDescription())).append(System.lineSeparator());
        sb.append(String.format("Release date: %s", game.getReleaseDate().format(dateTimeFormatter))).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
