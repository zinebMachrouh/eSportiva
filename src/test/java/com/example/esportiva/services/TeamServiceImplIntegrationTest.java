package com.example.esportiva.services;

import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.Team;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.models.enums.TournamentStatus;
import com.example.esportiva.repositories.interfaces.TeamRepository;
import com.example.esportiva.repositories.interfaces.TournamentRepository;
import com.example.esportiva.repositories.TeamRepositoryImpl;
import com.example.esportiva.repositories.TournamentRepositoryImpl;
import com.example.esportiva.services.TeamServiceImpl;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceImplIntegrationTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private TeamRepository teamRepository;
    private TournamentRepository tournamentRepository;
    private TeamServiceImpl teamService;

    private Team team;
    private Tournament tournament;

    @BeforeAll
    static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("esportivaTestPU");
    }

    @BeforeEach
    void setUp() throws SQLException {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Use the EntityManagerFactory directly
        teamRepository = new TeamRepositoryImpl(entityManagerFactory);
        tournamentRepository = new TournamentRepositoryImpl(entityManagerFactory);
        teamService = new TeamServiceImpl(teamRepository, tournamentRepository);

        team = new Team("Test Team", 1);
        Game game = new Game("FIFA 22", GameDifficulty.HARD, 5);
        tournament = new Tournament("Championship", Date.valueOf("2024-10-20"), Date.valueOf("2024-10-22"),
                1000, 180, 30, 15, TournamentStatus.SCHEDULED, 50000, game);

        teamRepository.addTeam(team);
        tournamentRepository.addTournament(tournament);
    }

    @Test
    void testAddTeam() throws Exception {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("New Team");
        teamDTO.setRanking(2);

        Team createdTeam = teamService.addTeam(teamDTO);

        assertNotNull(createdTeam.getId());
        assertEquals("New Team", createdTeam.getName());
        assertEquals(2, createdTeam.getRanking());
    }

    @Test
    void testAttachTournament() throws Exception {
        Team updatedTeam = teamService.attachTournament(team.getId(), tournament.getId());

        entityManager.refresh(updatedTeam); // Ensure the latest state

        assertEquals(1, updatedTeam.getTournaments().size());
        assertTrue(updatedTeam.getTournaments().contains(tournament));
    }

    @Test
    void testDetachTournament() throws Exception {
        teamService.attachTournament(team.getId(), tournament.getId());
        Team updatedTeam = teamService.detachTournament(team.getId(), tournament.getId());

        entityManager.refresh(updatedTeam);

        assertEquals(0, updatedTeam.getTournaments().size());
    }

    @Test
    void testGetAllTeams() throws SQLException {
        List<Team> teams = teamService.getAllTeams();
        assertNotNull(teams);
        assertFalse(teams.isEmpty());
    }

    @AfterEach
    void tearDown() {
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @AfterAll
    static void close() {
        entityManagerFactory.close();
    }
}
