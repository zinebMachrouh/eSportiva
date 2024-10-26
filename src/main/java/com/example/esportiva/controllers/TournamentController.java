package com.example.esportiva.controllers;

import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.Team;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.models.enums.TournamentStatus;
import com.example.esportiva.services.interfaces.GameService;
import com.example.esportiva.services.interfaces.TeamService;
import com.example.esportiva.services.interfaces.TournamentService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TournamentController {
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final TeamService teamService;

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public TournamentController(TournamentService tournamentService, GameService gameService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    public TournamentDTO addTournament() throws SQLException {
        logger.info("Adding a new tournament");

        String title = System.console().readLine("Enter the title of the tournament: ");
        while (InputValidation.handleString(title)) {
            title = System.console().readLine("Enter the title of the tournament: ");
        }

        String startDate = System.console().readLine("Enter the start date of the tournament: ");
        while (InputValidation.handleDate(startDate)) {
            startDate = System.console().readLine("Enter the start date of the tournament: ");
        }

        String endDate = System.console().readLine("Enter the end date of the tournament: ");
        while (InputValidation.handleDate(endDate)) {
            endDate = System.console().readLine("Enter the end date of the tournament: ");
        }

        Integer spectators = Integer.parseInt(System.console().readLine("Enter the number of spectators: "));
        while (InputValidation.handleNumber(spectators)) {
            spectators = Integer.parseInt(System.console().readLine("Enter the number of spectators: "));
        }

        Integer breakDuration = Integer.parseInt(System.console().readLine("Enter the break duration: "));
        while (InputValidation.handleNumber(breakDuration)) {
            breakDuration = Integer.parseInt(System.console().readLine("Enter the break duration: "));
        }

        Integer ceremonyDuration = Integer.parseInt(System.console().readLine("Enter the ceremony duration: "));
        while (InputValidation.handleNumber(ceremonyDuration)) {
            ceremonyDuration = Integer.parseInt(System.console().readLine("Enter the ceremony duration: "));
        }

        String status = System.console().readLine("Enter the status of the tournament (scheduled - ongoing - cancelled): ");
        while (InputValidation.handleString(status)) {
            status = System.console().readLine("Enter the status of the tournament (scheduled - ongoing - cancelled): ");
        }

        Double prize = Double.parseDouble(System.console().readLine("Enter the prize: "));
        while (InputValidation.handleNumber(prize)) {
            prize = Double.parseDouble(System.console().readLine("Enter the prize: "));
        }

        List<Game> games = gameService.getAllGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + ". " + games.get(i).getName());
        }
        Integer gameIndex = Integer.parseInt(System.console().readLine("Enter the index of the game: "));
        Game game = null;
        if (gameIndex < 0 || gameIndex >= games.size()) {
            logger.error("Invalid game index");
        } else if (String.valueOf(gameIndex).isEmpty()) {
            logger.error("Game index cannot be empty");
        } else {
            game = games.get(gameIndex - 1);
            logger.info("Successfully selected the game");
        }

        TournamentDTO tournament = new TournamentDTO(title, Date.valueOf(startDate), Date.valueOf(endDate), spectators,0, breakDuration, ceremonyDuration, TournamentStatus.valueOf(status), prize, GameDTO.modelToDTO(game));
        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournament);
        tournament.setEstimatedDuration(estimatedDuration);

        Tournament newTournament = tournamentService.addTournament(tournament);

        logger.info("Do you want to add teams to the tournament? (yes/no)");
        String addTeam = System.console().readLine();
        if (addTeam.equals("yes")) {
            logger.info("Adding teams to the tournament");
            while (addTeam.equals("yes")) {
                List<Team> teams = teamService.getAllTeams();
                for (int i = 0; i < teams.size(); i++) {
                    System.out.println(i + 1 + ". " + teams.get(i).getName());
                }
                Integer teamIndex = Integer.parseInt(System.console().readLine("Enter the index of the team: "));
                Team team = null;
                if (teamIndex < 0 || teamIndex >= teams.size()) {
                    logger.error("Invalid team index");
                } else if (String.valueOf(teamIndex).isEmpty()) {
                    logger.error("Team index cannot be empty");
                } else {
                    team = teams.get(teamIndex - 1);
                    logger.info("Successfully selected the team");
                }
                tournamentService.attachTeam(newTournament.getId(), team.getId());
                addTeam = System.console().readLine("Do you want to add more teams to the tournament? (yes/no)");
            }
        }

        if (newTournament == null) {
            logger.error("Failed to add a new tournament");
            return null;
        } else {
            logger.info("Successfully added a new tournament");
            return TournamentDTO.modelToDTO(newTournament);
        }
    }

    public TournamentDTO updateTournament() throws SQLException {
        logger.info("Updating a tournament");

        String id = System.console().readLine("Enter the id of the tournament: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the tournament: ");
        }

        Tournament tournamentEntity = tournamentService.getTournament(java.util.UUID.fromString(id));

        String title = System.console().readLine("Enter the title of the tournament: ");
        if (title.isEmpty()) {
            title = tournamentEntity.getTitle();
        } else {
            while (InputValidation.handleString(title)) {
                title = System.console().readLine("Enter the title of the tournament: ");
            }
        }

        String startDate = System.console().readLine("Enter the start date of the tournament: ");
        if (startDate.isEmpty()) {
            startDate = tournamentEntity.getStartDate().toString();
        } else {
            while (InputValidation.handleDate(startDate)) {
                startDate = System.console().readLine("Enter the start date of the tournament: ");
            }
        }

        String endDate = System.console().readLine("Enter the end date of the tournament: ");
        if (endDate.isEmpty()) {
            endDate = tournamentEntity.getEndDate().toString();
        } else {
            while (InputValidation.handleDate(endDate)) {
                endDate = System.console().readLine("Enter the end date of the tournament: ");
            }
        }

        Integer spectators = Integer.parseInt(System.console().readLine("Enter the number of spectators: "));
        if (String.valueOf(spectators).isEmpty()) {
            spectators = tournamentEntity.getSpectators();
        } else {
            while (InputValidation.handleNumber(spectators)) {
                spectators = Integer.parseInt(System.console().readLine("Enter the number of spectators: "));
            }
        }

        Integer breakDuration = Integer.parseInt(System.console().readLine("Enter the break duration: "));
        if (String.valueOf(breakDuration).isEmpty()) {
            breakDuration = tournamentEntity.getBreakDuration();
        } else {
            while (InputValidation.handleNumber(breakDuration)) {
                breakDuration = Integer.parseInt(System.console().readLine("Enter the break duration: "));
            }
        }

        Integer ceremonyDuration = Integer.parseInt(System.console().readLine("Enter the ceremony duration: "));
        if (String.valueOf(ceremonyDuration).isEmpty()) {
            ceremonyDuration = tournamentEntity.getCeremonyDuration();
        } else {
            while (InputValidation.handleNumber(ceremonyDuration)) {
                ceremonyDuration = Integer.parseInt(System.console().readLine("Enter the ceremony duration: "));
            }
        }

        String status = System.console().readLine("Enter the status of the tournament (scheduled - ongoing - finished - cancelled): ");
        if (status.isEmpty()) {
            status = tournamentEntity.getStatus().toString();
        } else {
            while (InputValidation.handleString(status)) {
                status = System.console().readLine("Enter the status of the tournament (scheduled - ongoing - finished - cancelled): ");
            }
        }

        if (status.equals("finished")) {
            List<Team> teams = tournamentEntity.getTeams();
            System.out.println("Enter Team's ranking");
            for (Team team : teams) {
                System.out.println(team.getName());
                Integer ranking = Integer.parseInt(System.console().readLine("Enter the ranking of the team: "));
                while (InputValidation.handleNumber(ranking)) {
                    ranking = Integer.parseInt(System.console().readLine("Enter the ranking of the team: "));
                }
                team.setRanking(ranking);
                teamService.updateTeam(TeamDTO.modelToDTO(team));
            }
        }

        Double prize = Double.parseDouble(System.console().readLine("Enter the prize: "));
        if (String.valueOf(prize).isEmpty()) {
            prize = tournamentEntity.getPrize();
        } else {
            while (InputValidation.handleNumber(prize)) {
                prize = Double.parseDouble(System.console().readLine("Enter the prize: "));
            }
        }

        List<Game> games = gameService.getAllGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println(i + 1 + ". " + games.get(i).getName());
        }
        Integer gameIndex = Integer.parseInt(System.console().readLine("Enter the index of the game: "));
        Game game = tournamentEntity.getGame();
        if (gameIndex < 0 || gameIndex >= games.size()) {
            logger.error("Invalid game index");
        } else if (String.valueOf(gameIndex).isEmpty()) {
            logger.error("Game index cannot be empty");
        } else {
            game = games.get(gameIndex - 1);
            logger.info("Successfully selected the game");
        }

        TournamentDTO tournament = new TournamentDTO(title, Date.valueOf(startDate), Date.valueOf(endDate), spectators,0, breakDuration, ceremonyDuration, TournamentStatus.valueOf(status), prize, GameDTO.modelToDTO(game));
        Integer estimatedDuration = tournamentService.getEstimatedDuration(tournament);
        tournament.setEstimatedDuration(estimatedDuration);

        Tournament updatedTournament = tournamentService.updateTournament(tournament);

        if (updatedTournament == null) {
            logger.error("Failed to update the tournament");
            return null;
        } else {
            logger.info("Successfully updated the tournament");
            return TournamentDTO.modelToDTO(updatedTournament);
        }
    }

    public TournamentDTO getTournament() throws SQLException {
        logger.info("Getting a tournament");

        String id = System.console().readLine("Enter the id of the tournament: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the tournament: ");
        }

        Tournament tournament = tournamentService.getTournament(java.util.UUID.fromString(id));

        if (tournament == null) {
            logger.error("Failed to get the tournament");
            return null;
        } else {
            logger.info("Successfully got the tournament");
            return TournamentDTO.modelToDTO(tournament);
        }
    }

    public List<TournamentDTO> getAllTournaments() throws SQLException {
        logger.info("Getting all tournaments");

        List<Tournament> tournaments = tournamentService.getAllTournaments();

        if (tournaments == null) {
            logger.error("Failed to get all tournaments");
            return null;
        } else {
            logger.info("Successfully got all tournaments");
            return tournaments.stream().map(TournamentDTO::modelToDTO).collect(Collectors.toList());
        }
    }

    public List<TournamentDTO> getUpcomingTournaments() throws SQLException {
        logger.info("Getting all upcoming tournaments");

        List<Tournament> tournaments = tournamentService.getUpcomingTournaments();

        if (tournaments == null) {
            logger.error("Failed to get all upcoming tournaments");
            return null;
        } else {
            logger.info("Successfully got all upcoming tournaments");
            return tournaments.stream().map(TournamentDTO::modelToDTO).collect(Collectors.toList());
        }
    }
}
