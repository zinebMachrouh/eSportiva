package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Tournament;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TournamentService {
    public Tournament addTournament(TournamentDTO tournament) throws SQLException;
    public Tournament updateTournament(TournamentDTO tournament) throws SQLException;
    public Tournament getTournament(UUID id) throws SQLException;
    public Tournament attachTeam(UUID tournamentId, UUID teamId) throws SQLException;
    public Tournament detachTeam(UUID tournamentId, UUID teamId) throws SQLException;
    public Tournament attachGame(UUID tournamentId, UUID gameId) throws SQLException;
    public Tournament detachGame(UUID tournamentId, UUID gameId) throws SQLException;
    public List<Tournament> getAllTournaments() throws SQLException;
    public List<Tournament> getUpcomingTournaments() throws SQLException;
}
