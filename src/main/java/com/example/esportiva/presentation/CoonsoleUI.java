package com.example.esportiva.presentation;

import com.example.esportiva.controllers.GameController;
import com.example.esportiva.controllers.GamerController;
import com.example.esportiva.controllers.TeamController;
import com.example.esportiva.controllers.TournamentController;
import com.example.esportiva.dto.GameDTO;
import com.example.esportiva.dto.GamerDTO;
import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.dto.TournamentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CoonsoleUI {
    private final GameController gameController;
    private final GamerController gamerController;
    private final TeamController teamController;
    private final TournamentController tournamentController;

    private static final Logger logger = LoggerFactory.getLogger(CoonsoleUI.class);
    private Scanner scanner = new Scanner(System.in);

    public CoonsoleUI(GameController gameController, GamerController gamerController, TeamController teamController, TournamentController tournamentController) {
        this.gameController = gameController;
        this.gamerController = gamerController;
        this.teamController = teamController;
        this.tournamentController = tournamentController;
    }

    public void run() throws SQLException {
        System.out.println("Welcome to ESportiva!");

        boolean running = true;
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Manage games");
            System.out.println("2. Manage gamers");
            System.out.println("3. Manage teams");
            System.out.println("4. Manage tournaments");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    manageGames();
                    break;
                case 2:
                    manageGamers();
                    break;
                case 3:
                    manageTeams();
                    break;
                case 4:
                    manageTournaments();
                    break;
                case 5:
                    System.out.println("See you later <3");
                    running = false;
                    break;
                default:
                    logger.error("Invalid option");
                    break;
            }
        }
    }

    private void manageGames() throws SQLException {
        System.out.println("Game Menu");
        System.out.println("1. Add Game");
        System.out.println("2. Update Game");
        System.out.println("3. Get Games");
        System.out.println("4. Get Game");
        System.out.println("5. Exit");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                GameDTO game = gameController.addGame();
                logger.info(game.toString());
                break;
            case 2:
                GameDTO game1 = gameController.updateGame();
                logger.info(game1.toString());
                break;
            case 3:
                gameController.getGames();
                break;
            case 4:
                gameController.getGame();
                break;
            case 5:
                break;
            default:
                logger.error("Invalid option");
                break;
        }
    }

    private void manageGamers() throws SQLException {
        System.out.println("Gamer Menu");
        System.out.println("1. Add Gamer");
        System.out.println("2. Update Gamer");
        System.out.println("3. Delete Gamer");
        System.out.println("4. Get Gamers");
        System.out.println("5. Get Gamer");
        System.out.println("6. Exit");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                gamerController.addGamer();
                break;
            case 2:
                gamerController.updateGamer();
                break;
            case 3:
                gamerController.deleteGamer();
                break;
            case 4:
                gamerController.getAllGamers();
                break;
            case 5:
                GamerDTO gamer = gamerController.getGamer();
                System.out.println(gamer.toString());
                break;
            case 6:
                break;
            default:
                logger.error("Invalid option");
                break;
        }
    }

    private void manageTeams() throws SQLException {
        System.out.println("Team Menu");
        System.out.println("1. Add Team");
        System.out.println("2. Update Team");
        System.out.println("3. Get Teams");
        System.out.println("4. Get Team");
        System.out.println("5. Exit");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                teamController.addTeam();
                break;
            case 2:
                teamController.updateTeam();
                break;
            case 3:
                teamController.getAllTeams();
                break;
            case 4:
                TeamDTO teamDTO = teamController.getTeam();
                System.out.println(teamDTO.toString());
                break;
            case 5:
                break;
            default:
                logger.error("Invalid option");
                break;
        }
    }

    private void manageTournaments() throws SQLException {
        System.out.println("Tournament Menu");
        System.out.println("1. Add Tournament");
        System.out.println("2. Update Tournament");
        System.out.println("3. Get Tournaments");
        System.out.println("4. Get Upcoming Tournaments");
        System.out.println("5. Get Tournament");
        System.out.println("6. Exit");
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                tournamentController.addTournament();
                break;
            case 2:
                tournamentController.updateTournament();
                break;
            case 3:
                tournamentController.getAllTournaments();
                break;
            case 4:
                tournamentController.getUpcomingTournaments();
                break;
            case 5:
                TournamentDTO tournament = tournamentController.getTournament();
                System.out.println(tournament.toString());
                break;
            case 6:
                break;
            default:
                logger.error("Invalid option");
                break;
        }
    }
}
