package com.example.esportiva.services;

import com.example.esportiva.dao.interfaces.TournamentDAO;
import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.repositories.interfaces.TournamentRepository;
import com.example.esportiva.services.interfaces.TournamentService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;
    private final TournamentDAO tournamentDAO;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentDAO tournamentDAO) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentDAO = tournamentDAO;
    }

    @Override
    public Tournament addTournament(TournamentDTO tournament) throws SQLException {
        if (tournament == null) {
            throw new IllegalArgumentException("Tournament cannot be null");
        }else {
            return tournamentRepository.addTournament(tournament.dtoToModel());
        }
    }

    @Override
    public Tournament updateTournament(TournamentDTO tournament) throws SQLException {
        if (tournament == null) {
            throw new IllegalArgumentException("Tournament cannot be null");
        }else {
            return tournamentRepository.updateTournament(tournament.dtoToModel());
        }
    }

    @Override
    public Tournament getTournament(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }else {
            return tournamentRepository.getTournament(id);
        }
    }

    @Override
    public Tournament attachTeam(Long tournamentId, Long teamId) throws SQLException {
        if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else {
            return tournamentRepository.attachTeam(tournamentId, teamId);
        }
    }

    @Override
    public Tournament detachTeam(Long tournamentId, Long teamId) throws SQLException {
        if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else if (teamId == null) {
            throw new IllegalArgumentException("Team Id cannot be null");
        }else {
            return tournamentRepository.detachTeam(tournamentId, teamId);
        }
    }

    @Override
    public Tournament attachGame(Long tournamentId, Long gameId) throws SQLException {
        if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else if (gameId == null) {
            throw new IllegalArgumentException("Game Id cannot be null");
        }else {
            return tournamentRepository.attachGame(tournamentId, gameId);
        }
    }

    @Override
    public Tournament detachGame(Long tournamentId, Long gameId) throws SQLException {
        if (tournamentId == null) {
            throw new IllegalArgumentException("Tournament Id cannot be null");
        }else if (gameId == null) {
            throw new IllegalArgumentException("Game Id cannot be null");
        }else {
            return tournamentRepository.detachGame(tournamentId, gameId);
        }
    }

    @Override
    public List<Tournament> getAllTournaments() throws SQLException {
        return tournamentRepository.getAllTournaments();
    }

    @Override
    public List<Tournament> getUpcomingTournaments() throws SQLException {
        return tournamentRepository.getUpcomingTournaments();
    }

    @Override
    public Integer getEstimatedDuration(TournamentDTO tournament) throws SQLException {
        if (tournament == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }else {
            Tournament tournament1 = tournament.dtoToModel();
            return tournamentDAO.calculateDurationEstimate(tournament1);
        }
    }
}
