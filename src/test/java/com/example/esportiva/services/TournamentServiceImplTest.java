
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    void testUpdateTournament() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.updateTournament(any(Tournament.class))).thenReturn(tournament);

        Tournament result = tournamentService.updateTournament(tournamentDTO);
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).updateTournament(any(Tournament.class));
    }
    
    @Test
    void testGetTournament() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.getTournament(any())).thenReturn(tournament);

        Tournament result = tournamentService.getTournament(tournament.getId());
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).getTournament(any());
    }
    
    @Test
    void testAttachTeam() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.attachTeam(any(), any())).thenReturn(tournament);

        Tournament result = tournamentService.attachTeam(tournament.getId(), game.getId());
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).attachTeam(any(), any());
    }
    
    @Test
    void testDetachTeam() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.detachTeam(any(), any())).thenReturn(tournament);

        Tournament result = tournamentService.detachTeam(tournament.getId(), game.getId());
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).detachTeam(any(), any());
    }
    
    @Test
    void testAttachGame() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.attachGame(any(), any())).thenReturn(tournament);

        Tournament result = tournamentService.attachGame(tournament.getId(), game.getId());
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).attachGame(any(), any());
    }
    
    @Test
    void testDetachGame() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);

        Tournament tournament = new Tournament("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, game);
        TournamentDTO tournamentDTO = new TournamentDTO("FIFA 22 Tournament", Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),500,0,15,25, TournamentStatus.ONGOING,2500, gameDTO);

        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournamentDTO);
        tournament.setEstimatedDuration(estimatedDuration);
        tournamentDTO.setEstimatedDuration(estimatedDuration);

        when(tournamentRepository.detachGame(any(), any())).thenReturn(tournament);

        Tournament result = tournamentService.detachGame(tournament.getId(), game.getId());
        assertNotNull(result);
        assertEquals("FIFA 22 Tournament", result.getTitle());
        verify(tournamentRepository).detachGame(any(), any());
    }

    @Test
    void testGetAllTournaments() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        Tournament tournament = new Tournament("FIFA 22 Tournament",
                Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),
                500, 0, 15, 25, TournamentStatus.ONGOING, 2500, game);
        tournament.setEstimatedDuration(1000);

        List<Tournament> tournaments = Collections.singletonList(tournament);

        when(tournamentRepository.getAllTournaments()).thenReturn(tournaments);

        List<Tournament> result = tournamentService.getAllTournaments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("FIFA 22 Tournament", result.get(0).getTitle());
        assertEquals(TournamentStatus.ONGOING, result.get(0).getStatus());
        verify(tournamentRepository).getAllTournaments();
    }

    @Test
    void testGetUpcomingTournaments() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);
        Tournament tournament = new Tournament("FIFA 22 Tournament",
                Date.valueOf("2022-12-12"), Date.valueOf("2022-12-20"),
                500, 0, 15, 25, TournamentStatus.ONGOING, 2500, game);
        tournament.setEstimatedDuration(1000);

        List<Tournament> tournaments = Collections.singletonList(tournament);

        when(tournamentRepository.getUpcomingTournaments()).thenReturn(tournaments);

        List<Tournament> result = tournamentService.getUpcomingTournaments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("FIFA 22 Tournament", result.get(0).getTitle());
        assertEquals(TournamentStatus.ONGOING, result.get(0).getStatus());
        verify(tournamentRepository).getUpcomingTournaments();
    }
}
