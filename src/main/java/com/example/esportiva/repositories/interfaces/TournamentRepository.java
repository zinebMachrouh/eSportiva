package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Tournament;

import java.util.List;
import java.util.UUID;

public interface TournamentRepository {
    public Tournament addTournament(Tournament tournament);
    public Tournament updateTournament(Tournament tournament);
    public Tournament getTournament(UUID id);
    public Tournament attachTeam(UUID tournamentId, UUID teamId);
    public Tournament detachTeam(UUID tournamentId, UUID teamId);
    public Tournament attachGame(UUID tournamentId, UUID gameId);
    public Tournament detachGame(UUID tournamentId, UUID gameId);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getUpcomingTournaments();
}
