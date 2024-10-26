package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Tournament;

import java.util.List;
import java.util.UUID;

public interface TournamentRepository {
    public Tournament addTournament(Tournament tournament);
    public Tournament updateTournament(Tournament tournament);
    public Tournament getTournament(Long id);
    public Tournament attachTeam(Long tournamentId, Long teamId);
    public Tournament detachTeam(Long tournamentId, Long teamId);
    public Tournament attachGame(Long tournamentId, Long gameId);
    public Tournament detachGame(Long tournamentId, Long gameId);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getUpcomingTournaments();
}
