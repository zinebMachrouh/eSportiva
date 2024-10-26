package com.example.esportiva.controllers;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.services.interfaces.GameService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.sql.SQLException;
import java.util.UUID;

public class GameController {
    private GameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GameController() {}

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public GameDTO addGame() throws SQLException {
        logger.info("Adding a new game");

        String name = System.console().readLine("Enter the name of the game: ");
        while (InputValidation.handleString(name)) {
            name = System.console().readLine("Name cannot be empty. Enter the name of the game: ");
        }

        String difficultyInput = System.console().readLine("Enter the difficulty of the game (easy-medium-hard-extreme): ");
        while(InputValidation.handleGameDifficulty(difficultyInput)) {
            difficultyInput = System.console().readLine("Invalid difficulty. Enter the difficulty of the game (easy-medium-hard-extreme): ");
        }
        GameDifficulty difficulty = GameDifficulty.valueOf(difficultyInput.toUpperCase());

        Integer matchDuration = Integer.parseInt(System.console().readLine("Enter the match duration of the game: "));
        while (InputValidation.handleNumber(matchDuration)) {
            matchDuration = Integer.parseInt(System.console().readLine("Match duration cannot be empty. Enter the match duration of the game: "));
        }

        GameDTO game = new GameDTO(name, difficulty, matchDuration);
        Game newGame = gameService.addGame(game);

        if (newGame == null) {
            logger.error("Failed to add a new game");
            return null;
        }else {
            logger.info("Successfully added a new game");
            return GameDTO.modelToDTO(newGame);
        }
    }

    public GameDTO updateGame() throws SQLException {
        logger.info("Updating a game");

        String id = System.console().readLine("Enter the id of the game: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the game: ");
        }

        Game gameEntity = gameService.getGame(UUID.fromString(id));

        String name = System.console().readLine("Enter the name of the game: ");
        if (name.isEmpty()){
            name = gameEntity.getName();
        }else {
            while (InputValidation.handleString(name)) {
                name = System.console().readLine("Name cannot be empty. Enter the name of the game: ");
            }
        }

        String difficultyInput = System.console().readLine("Enter the difficulty of the game (easy-medium-hard-extreme): ");
        if (difficultyInput.isEmpty()){
            difficultyInput = gameEntity.getDifficulty().toString();
        }else{
            while(InputValidation.handleGameDifficulty(difficultyInput)) {
                difficultyInput = System.console().readLine("Invalid difficulty. Enter the difficulty of the game (easy-medium-hard-extreme): ");
            }
        }

        Integer matchDuration = Integer.parseInt(System.console().readLine("Enter the match duration of the game: "));
        if (String.valueOf(matchDuration).isEmpty()){
            matchDuration = gameEntity.getMatchDuration();
        }else {
            while (InputValidation.handleNumber(matchDuration)) {
                matchDuration = Integer.parseInt(System.console().readLine("Match duration cannot be empty. Enter the match duration of the game: "));
            }
        }

        GameDTO game = new GameDTO(name, GameDifficulty.valueOf(difficultyInput), matchDuration);
        game.setId(UUID.fromString(id));
        Game updatedGame = gameService.updateGame(game);

        if (updatedGame == null) {
            logger.error("Failed to update the game");
            return null;
        }else {
            logger.info("Successfully updated the game");
            return GameDTO.modelToDTO(updatedGame);
        }
    }

    public GameDTO getGame() throws SQLException {
        logger.info("Getting a game");

        String id = System.console().readLine("Enter the id of the game: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the game: ");
        }

        Game game = gameService.getGame(UUID.fromString(id));

        if (game == null) {
            logger.error("Failed to get the game");
            return null;
        }else {
            logger.info("Successfully got the game");
            return GameDTO.modelToDTO(game);
        }
    }
}
