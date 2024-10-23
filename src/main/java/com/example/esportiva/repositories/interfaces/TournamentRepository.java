package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Tournament;

import java.util.List;

public interface TournamentRepository {
    public Tournament addTournament(Tournament tournament);
    public Tournament updateTournament(Tournament tournament);
    public Tournament getTournament(String id);
    public Tournament attachTeam(String tournamentId, String teamId);
    public Tournament detachTeam(String tournamentId, String teamId);
    public Tournament attachGame(String tournamentId, String gameId);
    public Tournament detachGame(String tournamentId, String gameId);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getUpcomingTournaments();
}
