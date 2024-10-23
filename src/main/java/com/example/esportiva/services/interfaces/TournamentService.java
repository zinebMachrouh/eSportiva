package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Tournament;

import java.util.List;
import java.util.UUID;

public interface TournamentService {
    public Tournament addTournament(TournamentDTO tournament);
    public Tournament updateTournament(TournamentDTO tournament);
    public Tournament getTournament(UUID id);
    public Tournament attachTeam(UUID tournamentId, UUID teamId);
    public Tournament detachTeam(UUID tournamentId, UUID teamId);
    public Tournament attachGame(UUID tournamentId, UUID gameId);
    public Tournament detachGame(UUID tournamentId, UUID gameId);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getUpcomingTournaments();
}
