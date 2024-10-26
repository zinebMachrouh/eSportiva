package com.example.esportiva.controllers;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.services.interfaces.GameService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameController {
    private GameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final Scanner scanner = new Scanner(System.in);

    public GameController() {}

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public GameDTO addGame() throws SQLException {
        logger.info("Adding a new game");

        System.out.print("Enter the name of the game: ");
        String name = scanner.nextLine();
        while (!InputValidation.handleString(name)) {
            System.out.print("Name cannot be empty. Enter the name of the game: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter the difficulty of the game (easy-medium-hard-extreme): ");
        String difficultyInput = scanner.nextLine();
        while (!InputValidation.handleGameDifficulty(difficultyInput)) {
            System.out.print("Invalid difficulty. Enter the difficulty of the game (easy-medium-hard-extreme): ");
            difficultyInput = scanner.nextLine();
        }
        GameDifficulty difficulty = GameDifficulty.valueOf(difficultyInput.toUpperCase());

        System.out.print("Enter the match duration of the game: ");
        Integer matchDuration = Integer.parseInt(scanner.nextLine());
        while (!InputValidation.handleNumber(matchDuration)) {
            System.out.print("Match duration cannot be empty. Enter the match duration of the game: ");
            matchDuration = Integer.parseInt(scanner.nextLine());
        }

        GameDTO game = new GameDTO(name, difficulty, matchDuration);
        Game newGame = gameService.addGame(game);

        if (newGame == null) {
            logger.error("Failed to add a new game");
            return null;
        } else {
            logger.info("Successfully added a new game");
            return GameDTO.modelToDTO(newGame);
        }
    }

    public GameDTO updateGame() throws SQLException {
        logger.info("Updating a game");

        System.out.println("Enter the id of the game: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Clear the newline character
//        while (!InputValidation.handleUUID(id)) {
//            System.out.println("Invalid id format. Please enter a valid UUID: ");
//            id = scanner.nextLine();
//        }

        Game gameEntity = gameService.getGame(id);

        if (gameEntity == null) {
            logger.error("Could not find the game");
            return null;
        }else {
            System.out.print("Enter the name of the game: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                name = gameEntity.getName();
            } else {
                while (!InputValidation.handleString(name)) {
                    System.out.print("Name cannot be empty. Enter the name of the game: ");
                    name = scanner.nextLine();
                }
            }

            System.out.print("Enter the difficulty of the game (easy-medium-hard-extreme): ");
            String difficultyInput = scanner.nextLine();
            if (difficultyInput.isEmpty()) {
                difficultyInput = gameEntity.getDifficulty().toString();
            } else {
                while (!InputValidation.handleGameDifficulty(difficultyInput)) {
                    System.out.print("Invalid difficulty. Enter the difficulty of the game (easy-medium-hard-extreme): ");
                    difficultyInput = scanner.nextLine();
                }
            }

            System.out.print("Enter the match duration of the game: ");
            String matchDurationInput = scanner.nextLine();
            Integer matchDuration = matchDurationInput.isEmpty()
                    ? gameEntity.getMatchDuration()
                    : Integer.parseInt(matchDurationInput);

            while (!InputValidation.handleNumber(matchDuration)) {
                System.out.print("Match duration cannot be empty. Enter the match duration of the game: ");
                matchDuration = Integer.parseInt(scanner.nextLine());
            }

            GameDTO game = new GameDTO(name, GameDifficulty.valueOf(difficultyInput.toUpperCase()), matchDuration);
            game.setId(id);
            Game updatedGame = gameService.updateGame(game);

            if (updatedGame == null) {
                logger.error("Failed to update the game");
                return null;
            } else {
                logger.info("Successfully updated the game");
                return GameDTO.modelToDTO(updatedGame);
            }
        }
    }

    public GameDTO getGame() throws SQLException {
        logger.info("Getting a game");

        System.out.print("Enter the id of the game: ");
        Long id = scanner.nextLong();

        Game game = gameService.getGame(id);

        System.out.println(game);

        if (game == null) {
            logger.error("Failed to get the game");
            return null;
        } else {
            logger.info("Successfully got the game");
            return GameDTO.modelToDTO(game);
        }
    }

    public void getGames() throws SQLException {
        logger.info("Getting all games");
        List<Game> games = gameService.getAllGames();
        games.stream().map(GameDTO::modelToDTO)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
