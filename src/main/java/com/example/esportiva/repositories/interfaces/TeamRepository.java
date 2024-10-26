package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Team;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TeamRepository {
    public Team addTeam(Team team) throws SQLException;
    public Team updateTeam(Team team) throws SQLException;
    public Team getTeam(Long id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public Team attachGamer(Long teamId, Long playerId) throws SQLException;
    public Team detachGamer(Long teamId, Long playerId) throws SQLException;
    public Team attachTournament(Long teamId, Long tournamentId) throws SQLException;
    public Team detachTournament(Long teamId, Long tournamentId) throws SQLException;
}
