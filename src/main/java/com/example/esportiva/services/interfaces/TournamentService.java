package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Tournament;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TournamentService {
    public Tournament addTournament(TournamentDTO tournament) throws SQLException;
    public Tournament updateTournament(TournamentDTO tournament) throws SQLException;
    public Tournament getTournament(Long id) throws SQLException;
    public Tournament attachTeam(Long tournamentId, Long teamId) throws SQLException;
    public Tournament detachTeam(Long tournamentId, Long teamId) throws SQLException;
    public Tournament attachGame(Long tournamentId, Long gameId) throws SQLException;
    public Tournament detachGame(Long tournamentId, Long gameId) throws SQLException;
    public List<Tournament> getAllTournaments() throws SQLException;
    public List<Tournament> getUpcomingTournaments() throws SQLException;
    public Integer getEstimatedDuration(TournamentDTO tournament) throws SQLException;
}
