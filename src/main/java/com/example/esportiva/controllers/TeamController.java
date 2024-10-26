package com.example.esportiva.controllers;

import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Gamer;
import com.example.esportiva.models.Team;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.services.interfaces.GameService;
import com.example.esportiva.services.interfaces.GamerService;
import com.example.esportiva.services.interfaces.TeamService;
import com.example.esportiva.services.interfaces.TournamentService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeamController {
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final GamerService gamerService;

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final Scanner scanner = new Scanner(System.in);

    public TeamController(TeamService teamService, TournamentService tournamentService, GamerService gamerService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.gamerService = gamerService;
    }

    public TeamDTO addTeam() throws SQLException {
        logger.info("Adding a new team");

        System.out.print("Enter the name of the team: ");
        String name = scanner.nextLine();
        while (!InputValidation.handleString(name)) {
            System.out.print("Name cannot be empty. Enter the name of the team: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter the ranking of the team: ");
        Integer ranking = scanner.nextInt();
        scanner.nextLine();
        while (!InputValidation.handleNumber(ranking)) {
            System.out.print("Ranking cannot be empty. Enter the ranking of the team: ");
            ranking = scanner.nextInt();
            scanner.nextLine();
        }

        logger.info("Select the team's tournament");
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        for (int i = 0; i < tournaments.size(); i++) {
            System.out.println((i + 1) + ". " + tournaments.get(i).getTitle());
        }

        System.out.print("Enter the index of the tournament: ");
        int tournamentIndex = scanner.nextInt();
        scanner.nextLine();

        Tournament tournament;
        if (tournamentIndex <= 0 || tournamentIndex > tournaments.size()) {
            logger.error("Invalid tournament index");
            return null;
        } else {
            tournament = tournaments.get(tournamentIndex - 1);
            logger.info("Successfully selected the team's tournament");
        }

        TeamDTO team = new TeamDTO(name, ranking);
        Team newTeam = teamService.addTeam(team);
        teamService.attachTournament(newTeam.getId(), tournament.getId());

        System.out.print("Do you want to add new gamers to the team? (yes/no): ");
        String addGamer = scanner.nextLine();
        while ("yes".equalsIgnoreCase(addGamer)) {
            System.out.print("Enter the Id of the gamer: ");
            Long gamerId = scanner.nextLong();
            scanner.nextLine();

            Gamer gamer = gamerService.getGamer(gamerId);
            if (gamer.getTeam() != null) {
                System.out.println("Gamer is already in a team");
            }else {
                teamService.attachGamer(newTeam.getId(), gamerId);
            }

            System.out.print("Do you want to add more gamers to the team? (yes/no): ");
            addGamer = scanner.nextLine();
        }

        if (newTeam == null) {
            logger.error("Failed to add a new team");
            return null;
        } else {
            logger.info("Successfully added a new team");
            return TeamDTO.modelToDTO(newTeam);
        }
    }

    public Team updateTeam() throws SQLException {
        logger.info("Updating a team");

        System.out.print("Enter the id of the team: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over

        Team teamEntity = teamService.getTeam(id);

        System.out.print("Enter the name of the team: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = teamEntity.getName();
        } else {
            while (!InputValidation.handleString(name)) {
                System.out.print("Name cannot be empty. Enter the name of the team: ");
                name = scanner.nextLine();
            }
        }


        Integer ranking = teamEntity.getRanking();
        if (teamEntity.getRanking() != 0){
            System.out.print("Enter the ranking of the team: ");
            ranking = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            if (ranking == null) {
                ranking = teamEntity.getRanking();
            } else {
                while (!InputValidation.handleNumber(ranking)) {
                    System.out.print("Ranking cannot be empty. Enter the ranking of the team: ");
                    ranking = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                }
            }
        }

        System.out.print("Do you want to remove gamers from the team? (yes/no): ");
        String removeGamer = scanner.nextLine();
        while ("yes".equalsIgnoreCase(removeGamer)) {
            System.out.print("Enter the Id of the gamer: ");
            Long gamerId = scanner.nextLong();
            scanner.nextLine(); // Consume newline left-over

            teamService.detachGamer(id, gamerId);
            System.out.print("Do you want to remove more gamers from the team? (yes/no): ");
            removeGamer = scanner.nextLine();
        }

        System.out.print("Do you want to add gamers to the team? (yes/no): ");
        String addGamer = scanner.nextLine();
        while ("yes".equalsIgnoreCase(addGamer)) {
            System.out.print("Enter the Id of the gamer: ");
            Long gamerId = scanner.nextLong();
            scanner.nextLine();

            Gamer gamer = gamerService.getGamer(gamerId);
            if (gamer.getTeam() != null) {
                System.out.println("Gamer is already in a team");
            }else {
                teamService.attachGamer(id, gamerId);
            }
            System.out.print("Do you want to add more gamers to the team? (yes/no): ");
            addGamer = scanner.nextLine();
        }

        TeamDTO team = new TeamDTO(id,name, ranking);
        Team updatedTeam = teamService.updateTeam(team);

        if (updatedTeam == null) {
            logger.error("Failed to update the team");
            return null;
        } else {
            logger.info("Successfully updated the team");
            return updatedTeam;
        }
    }

    public TeamDTO getTeam() throws SQLException {
        logger.info("Getting a team");

        System.out.print("Enter the id of the team: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over

        Team team = teamService.getTeam(id);

        if (team == null) {
            logger.error("Failed to get the team");
            return null;
        } else {
            logger.info("Successfully got the team");
            return TeamDTO.modelToDTO(team);
        }
    }

    public void getAllTeams() throws SQLException {
        logger.info("Getting all teams");

        List<Team> teams = teamService.getAllTeams();

        if (teams == null) {
            logger.error("Failed to get all teams");
        } else {
            logger.info("Successfully got all teams");
             teams.stream().map(TeamDTO::modelToDTO).collect(Collectors.toList()).forEach(System.out::println);
        }
    }
}
