
package com.example.esportiva.services;

import com.example.esportiva.dao.interfaces.TournamentDAO;
import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.models.enums.TournamentStatus;
import com.example.esportiva.repositories.interfaces.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TournamentServiceImplTest {
    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentDAO tournamentDAO;

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTournament() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.addTournament(any(Tournament.class))).thenReturn(tournament);

        Tournament result = tournamentService.addTournament(tournamentDTO);
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).addTournament(any(Tournament.class));
    }


}
