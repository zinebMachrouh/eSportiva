package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Game;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface GameRepository {
    public Game addGame(Game game) throws SQLException;
    public Game updateGame(Game game) throws SQLException;
    public Game getGame(Long id) throws SQLException;
    public List<Game> getAllGames() throws SQLException;
}
