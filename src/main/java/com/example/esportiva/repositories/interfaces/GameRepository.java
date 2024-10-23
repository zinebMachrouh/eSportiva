package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Game;

import java.sql.SQLException;
import java.util.UUID;

public interface GameRepository {
    public Game addGame(Game game) throws SQLException;
    public Game updateGame(Game game) throws SQLException;
    public Game getGame(UUID id) throws SQLException;
}
