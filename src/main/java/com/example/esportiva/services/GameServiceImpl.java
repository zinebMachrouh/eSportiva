package com.example.esportiva.services;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.dto.GamerDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.Gamer;
import com.example.esportiva.repositories.interfaces.GameRepository;
import com.example.esportiva.services.interfaces.GameService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addGame(GameDTO game) throws SQLException {
        if (game == null) {
            throw new SQLException("GameDTO is null");
        }else {
            return gameRepository.addGame(game.dtoToModel());
        }
    }

    @Override
    public Game updateGame(GameDTO game) throws SQLException {
        if (game == null) {
            throw new SQLException("GameDTO is null");
        }else {
            return gameRepository.updateGame(game.dtoToModel());
        }
    }

    @Override
    public Game getGame(Long id) throws SQLException {
        if (id == null) {
            throw new SQLException("Game id is null");
        }else {
            return gameRepository.getGame(id);
        }
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
        return gameRepository.getAllGames();
    }
}
