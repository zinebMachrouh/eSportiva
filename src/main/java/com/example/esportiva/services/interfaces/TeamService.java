package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.models.Team;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TeamService {
    public Team addTeam(TeamDTO team) throws SQLException;
    public Team updateTeam(TeamDTO team) throws SQLException;
    public Team getTeam(UUID id) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
    public Team attachGamer(UUID teamId, UUID playerId) throws SQLException;
    public Team detachGamer(UUID teamId, UUID playerId) throws SQLException;
    public Team attachTournament(UUID teamId, UUID tournamentId) throws SQLException;
    public Team detachTournament(UUID teamId, UUID tournamentId) throws SQLException;
}
