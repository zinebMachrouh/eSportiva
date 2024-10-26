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
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TournamentController {
    private final TournamentService tournamentService;
    private final GameService gameService;
    private final TeamService teamService;

    private static final Logger logger = LoggerFactory.getLogger(TournamentController.class);
    private static final Scanner scanner = new Scanner(System.in);

    public TournamentController(TournamentService tournamentService, GameService gameService, TeamService teamService) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    public TournamentDTO addTournament() throws SQLException {
        logger.info("Adding a new tournament");

        System.out.print("Enter the title of the tournament: ");
        String title = scanner.nextLine();
        while (!InputValidation.handleString(title)) {
            System.out.print("Enter the title of the tournament: ");
            title = scanner.nextLine();
        }

        System.out.print("Enter the start date of the tournament (yyyy-mm-dd): ");
        String startDate = scanner.nextLine();
        while (!InputValidation.handleDate(startDate)) {
            System.out.print("Enter the start date of the tournament (yyyy-mm-dd): ");
            startDate = scanner.nextLine();
        }

        System.out.print("Enter the end date of the tournament (yyyy-mm-dd): ");
        String endDate = scanner.nextLine();
        while (!InputValidation.handleDate(endDate)) {
            System.out.print("Enter the end date of the tournament (yyyy-mm-dd): ");
            endDate = scanner.nextLine();
        }

        System.out.print("Enter the number of spectators: ");
        Integer spectators = scanner.nextInt();
        while (!InputValidation.handleNumber(spectators)) {
            System.out.print("Enter the number of spectators: ");
            spectators = scanner.nextInt();
        }

        System.out.print("Enter the break duration: ");
        Integer breakDuration = scanner.nextInt();
        while (!InputValidation.handleNumber(breakDuration)) {
            System.out.print("Enter the break duration: ");
            breakDuration = scanner.nextInt();
        }

        System.out.print("Enter the ceremony duration: ");
        Integer ceremonyDuration = scanner.nextInt();
        while (!InputValidation.handleNumber(ceremonyDuration)) {
            System.out.print("Enter the ceremony duration: ");
            ceremonyDuration = scanner.nextInt();
        }

        scanner.nextLine();
        System.out.print("Enter the status of the tournament (scheduled - ongoing - cancelled): ");
        String status = scanner.nextLine();
        while (!InputValidation.handleString(status)) {
            System.out.print("Enter the status of the tournament (scheduled - ongoing - cancelled): ");
            status = scanner.nextLine();
        }

        System.out.print("Enter the prize: ");
        Double prize = scanner.nextDouble();
        while (!InputValidation.handleNumber(prize)) {
            System.out.print("Enter the prize: ");
            prize = scanner.nextDouble();
        }

        List<Game> games = gameService.getAllGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i).getName());
        }
        System.out.print("Enter the index of the game: ");
        int gameIndex = scanner.nextInt();
        Game game = null;
        if (gameIndex > 0 && gameIndex <= games.size()) {
            game = games.get(gameIndex - 1);
            logger.info("Successfully selected the game");
        } else {
            logger.error("Invalid game index");
        }

        TournamentDTO tournament = new TournamentDTO(
                title, Date.valueOf(startDate), Date.valueOf(endDate),
                spectators, 0, breakDuration, ceremonyDuration,
                TournamentStatus.valueOf(status.toUpperCase()), prize,
                GameDTO.modelToDTO(game)
        );
        tournament.setEstimatedDuration(tournamentService.getEstimatedDuration(tournament));

        Tournament newTournament = tournamentService.addTournament(tournament);

        System.out.print("Do you want to add teams to the tournament? (yes/no): ");
        String addTeam = scanner.next();
        scanner.nextLine();

        while ("yes".equalsIgnoreCase(addTeam)) {
            List<Team> teams = teamService.getAllTeams();
            for (int i = 0; i < teams.size(); i++) {
                System.out.println((i + 1) + ". " + teams.get(i).getName());
            }
            System.out.print("Enter the index of the team: ");
            int teamIndex = scanner.nextInt();
            Team team = null;
            if (teamIndex > 0 && teamIndex <= teams.size()) {
                team = teams.get(teamIndex - 1);
                tournamentService.attachTeam(newTournament.getId(), team.getId());
                logger.info("Successfully attached team");
            } else {
                logger.error("Invalid team index");
            }
            System.out.print("Do you want to add more teams to the tournament? (yes/no): ");
            addTeam = scanner.next();
            scanner.nextLine();
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

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ID of the tournament: ");
        Long id = null;
        while (true) {
            try {
                id = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid ID format. Enter a valid numeric ID: ");
            }
        }

        Tournament tournamentEntity = tournamentService.getTournament(id);
        if (tournamentEntity == null) {
            logger.error("Tournament not found");
            return null;
        }

        System.out.print("Enter the title of the tournament (leave empty to keep current): ");
        String title = scanner.nextLine();
        title = title.isEmpty() ? tournamentEntity.getTitle() : title;

        System.out.print("Enter the start date of the tournament (YYYY-MM-DD) or leave empty to keep current: ");
        String startDateInput = scanner.nextLine();
        Date startDate = parseDateInput(startDateInput, tournamentEntity.getStartDate());

        System.out.print("Enter the end date of the tournament (YYYY-MM-DD) or leave empty to keep current: ");
        String endDateInput = scanner.nextLine();
        Date endDate = parseDateInput(endDateInput, tournamentEntity.getEndDate());

        System.out.print("Enter the number of spectators (leave empty to keep current): ");
        Integer spectators = parseIntegerInput(scanner, tournamentEntity.getSpectators());

        System.out.print("Enter the break duration in minutes (leave empty to keep current): ");
        Integer breakDuration = parseIntegerInput(scanner, tournamentEntity.getBreakDuration());

        System.out.print("Enter the ceremony duration in minutes (leave empty to keep current): ");
        Integer ceremonyDuration = parseIntegerInput(scanner, tournamentEntity.getCeremonyDuration());

        System.out.print("Enter the status of the tournament (scheduled, ongoing, finished, cancelled) or leave empty to keep current: ");
        String status = scanner.nextLine();
        status = status.isEmpty() ? tournamentEntity.getStatus().toString() : status;


        System.out.print("Enter the prize amount (leave empty to keep current): ");
        Double prize = parseDoubleInput(scanner, tournamentEntity.getPrize());

        TournamentDTO tournament = new TournamentDTO(
                id,
                title,
                startDate,
                endDate,
                spectators,
                0,
                breakDuration,
                ceremonyDuration,
                TournamentStatus.valueOf(status.toUpperCase()),
                prize,
                GameDTO.modelToDTO(tournamentEntity.getGame())
        );

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

    private static Date parseDateInput(String input, Date currentValue) {
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            return new Date(dateFormat.parse(input).getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Keeping current date.");
            return currentValue;
        }
    }

    private Integer parseIntegerInput(Scanner scanner, Integer currentValue) {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Keeping current value.");
            return currentValue;
        }
    }

    private Double parseDoubleInput(Scanner scanner, Double currentValue) {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Keeping current value.");
            return currentValue;
        }
    }

    public TournamentDTO getTournament() throws SQLException {
        logger.info("Getting a tournament");

        System.out.print("Enter the id of the tournament: ");
        Long id = scanner.nextLong();
        while (!InputValidation.handleLong(id)) {
            System.out.print("Invalid id. Enter the id of the tournament: ");
            id = scanner.nextLong();
        }
        scanner.nextLine();
        Tournament tournament = tournamentService.getTournament(id);

        if (tournament == null) {
            logger.error("Failed to get the tournament");
            return null;
        } else {
            logger.info("Successfully got the tournament");
            return TournamentDTO.modelToDTO(tournament);
        }
    }

    public void getAllTournaments() throws SQLException {
        logger.info("Getting all tournaments");

        List<Tournament> tournaments = tournamentService.getAllTournaments();

        if (tournaments == null) {
            logger.error("Failed to get all tournaments");
        } else {
            logger.info("Successfully got all tournaments");
            tournaments.stream().map(TournamentDTO::modelToDTO).collect(Collectors.toList()).forEach(System.out::println);
        }
    }

    public void getUpcomingTournaments() throws SQLException {
        logger.info("Getting upcoming tournaments");

        List<Tournament> tournaments = tournamentService.getUpcomingTournaments();

        if (tournaments == null) {
            logger.error("Failed to get upcoming tournaments");
        } else {
            logger.info("Successfully got upcoming tournaments");
            tournaments.stream().map(TournamentDTO::modelToDTO).collect(Collectors.toList()).forEach(System.out::println);
        }
    }
}
