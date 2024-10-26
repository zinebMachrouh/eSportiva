package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.models.Game;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface GameService {
    public Game addGame(GameDTO game) throws SQLException;
    public Game updateGame(GameDTO game) throws SQLException;
    public Game getGame(UUID id) throws SQLException;
    public List<Game> getAllGames() throws SQLException;
}
