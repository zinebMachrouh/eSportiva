package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Team;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TeamRepository {
    public Team addTeam(Team team) throws SQLException;
    public Team updateTeam(Team team) throws SQLException;
    public Team getTeam(UUID id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public Team attachGamer(UUID teamId, UUID playerId) throws SQLException;
    public Team detachGamer(UUID teamId, UUID playerId) throws SQLException;
}
