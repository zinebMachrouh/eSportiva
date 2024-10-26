package com.example.esportiva.dao;

import com.example.esportiva.dao.interfaces.TournamentDAO;
import com.example.esportiva.models.Tournament;

public class TournamentDAOImpl implements TournamentDAO {
    @Override
    public Integer calculateDurationEstimate(Tournament tournament) {
        int numberOfTeams = tournament.getTeams().size();
        int matchDuration = tournament.getGame().getMatchDuration();
        int breakDuration = tournament.getBreakDuration();

        return (numberOfTeams * matchDuration) + breakDuration;
    }
}
