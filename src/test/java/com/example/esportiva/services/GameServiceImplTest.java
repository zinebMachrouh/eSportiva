package com.example.esportiva.services;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.repositories.interfaces.GameRepository;
import com.example.esportiva.services.interfaces.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGame() throws SQLException {
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);

        when(gameRepository.addGame(any(Game.class))).thenReturn(game);

        Game result = gameService.addGame(gameDTO);

        assertNotNull(result);
        assertEquals("FIFA 22", result.getName());
        verify(gameRepository).addGame(any(Game.class));
    }

    @Test
    void testUpdateGame() throws SQLException {
        GameDTO gameDTO = new GameDTO("FIFA 22", GameDifficulty.EASY, 5);
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);

        when(gameRepository.updateGame(any(Game.class))).thenReturn(game);

        Game result = gameService.updateGame(gameDTO);

        assertNotNull(result);
        assertEquals("FIFA 22", result.getName());
        verify(gameRepository).updateGame(any(Game.class));
    }

    @Test
    void testGetGame() throws SQLException {
        Game game = new Game("FIFA 22", GameDifficulty.EASY, 5);

        when(gameRepository.getGame(any())).thenReturn(game);

        Game result = gameService.getGame(game.getId());

        assertNotNull(result);
        assertEquals("FIFA 22", result.getName());
        verify(gameRepository).getGame(any());
    }


}
