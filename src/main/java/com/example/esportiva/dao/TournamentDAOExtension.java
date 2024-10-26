package com.example.esportiva.dao;

import com.example.esportiva.dao.interfaces.TournamentDAO;
import com.example.esportiva.models.Tournament;

public class TournamentDAOExtension implements TournamentDAO {
    @Override
    public Integer calculateDurationEstimate(Tournament tournament) {
        int numberOfTeams = tournament.getTeams().size();
        int matchDuration = tournament.getGame().getMatchDuration();
        int difficultyFactor = tournament.getGame().getDifficulty().getMultiplier();
        int breakDuration = tournament.getBreakDuration();
        int ceremonyDuration = tournament.getCeremonyDuration();

        return (numberOfTeams * matchDuration * difficultyFactor) + breakDuration + ceremonyDuration;
    }
}
