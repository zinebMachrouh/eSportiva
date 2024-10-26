package com.example.esportiva.dao.interfaces;

import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Tournament;

public interface TournamentDAO {
    public Integer calculateDurationEstimate(Tournament tournament);
}
