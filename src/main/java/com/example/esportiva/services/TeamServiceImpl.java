package com.example.esportiva.services;

import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.models.Team;
import com.example.esportiva.repositories.interfaces.TeamRepository;
import com.example.esportiva.services.interfaces.TeamService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public Team addTeam(TeamDTO team) throws SQLException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }else {
            return teamRepository.addTeam(team.dtoToModel());
        }
    }

    @Override
    public Team updateTeam(TeamDTO team) throws SQLException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }else {
            return teamRepository.updateTeam(team.dtoToModel());
        }
    }

    @Override
    public Team getTeam(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }else {
            return teamRepository.getTeam(id);
        }
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamRepository.getAllTeams();
    }

    @Override
    public Team attachGamer(Long teamId, Long playerId) throws SQLException {
        if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else if (playerId == null) {
            throw new IllegalArgumentException("Player Id cannot be null");
        }else {
            return teamRepository.attachGamer(teamId, playerId);
        }
    }

    @Override
    public Team detachGamer(Long teamId, Long playerId) throws SQLException {
        if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else if (playerId == null) {
            throw new IllegalArgumentException("Player Id cannot be null");
        }else {
            return teamRepository.detachGamer(teamId, playerId);
        }
    }

    @Override
    public Team attachTournament(Long teamId, Long tournamentId) throws SQLException {
        if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else {
            return teamRepository.attachTournament(teamId, tournamentId);
        }
    }

    @Override
    public Team detachTournament(Long teamId, Long tournamentId) throws SQLException {
        if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else {
            return teamRepository.detachTournament(teamId, tournamentId);
        }
    }
}
