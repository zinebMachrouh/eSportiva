package com.example.esportiva.controllers;

import com.example.esportiva.dto.GamerDTO;
import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.models.Gamer;
import com.example.esportiva.models.Team;
import com.example.esportiva.services.interfaces.GamerService;
import com.example.esportiva.services.interfaces.TeamService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GamerController {
    private final GamerService gamerService;
    private final TeamService teamService;
    private static final Logger logger = LoggerFactory.getLogger(GamerController.class);
    private final Scanner scanner = new Scanner(System.in);

    public GamerController(GamerService gamerService, TeamService teamService) {
        this.gamerService = gamerService;
        this.teamService = teamService;
    }

    public GamerDTO addGamer() throws SQLException {
        logger.info("Adding a gamer");

        System.out.print("Enter the username: ");
        String username = scanner.nextLine();
        while (!InputValidation.handleString(username)) {
            System.out.print("Invalid input. Enter the username: ");
            username = scanner.nextLine();
        }

        System.out.print("Enter the age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        while (!InputValidation.handleAge(age)) {
            System.out.print("Invalid age. Enter the age (10-99): ");
            age = scanner.nextInt();
            scanner.nextLine();
        }

        List<Team> teams = teamService.getAllTeams();
        TeamDTO team = null;
        if (!teams.isEmpty()) {
            logger.info("Select the team for the gamer");

            for (int i = 0; i < teams.size(); i++) {
                System.out.println("Team " + (i + 1) + ": " + teams.get(i).getName());
            }
            System.out.print("Pick a team (1 to " + teams.size() + "): ");
            int teamIndex = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (teamIndex > 0 && teamIndex <= teams.size()) {
                Team selectedTeam = teams.get(teamIndex - 1);
                team = new TeamDTO(selectedTeam.getId(), selectedTeam.getName(), selectedTeam.getRanking());
            } else {
                logger.error("Invalid team index");
            }
        }

        Gamer gamer = null;

        if (team == null) {
            gamer = gamerService.addGamer(new GamerDTO(username, age));
        } else {
            gamer = gamerService.addGamer(new GamerDTO(username, age, team));
        }

        if (gamer == null) {
            logger.error("Failed to add gamer");
            return null;
        } else {
            logger.info("Gamer added successfully");
            return GamerDTO.modelToDTO(gamer);
        }
    }

    public GamerDTO updateGamer() throws SQLException {
        logger.info("Updating a gamer");

        System.out.print("Enter the ID of the gamer: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Gamer gamerEntity = gamerService.getGamer(id);

        System.out.print("Enter the username (leave blank to keep current): ");
        String username = scanner.nextLine();
        if (username.isEmpty()) {
            username = gamerEntity.getUsername();
        } else {
            while (!InputValidation.handleString(username)) {
                System.out.print("Invalid input. Enter the username: ");
                username = scanner.nextLine();
            }
        }

        System.out.print("Enter the age (leave blank to keep current): ");
        String ageInput = scanner.nextLine();
        int age;
        if (ageInput.isEmpty()) {
            age = gamerEntity.getAge();
        } else {
            age = Integer.parseInt(ageInput);
            while (!InputValidation.handleAge(age)) {
                System.out.print("Invalid age. Enter the age (10-99): ");
                age = scanner.nextInt();
                scanner.nextLine();
            }
        }


        List<Team> teams = teamService.getAllTeams();
        TeamDTO team = null;
        if (!teams.isEmpty()){
            logger.info("Select the team for the gamer");
            for (int i = 0; i < teams.size(); i++) {
                System.out.println("Team " + (i + 1) + ": " + teams.get(i).getName());
            }
            System.out.print("Pick a team (1 to " + teams.size() + ") or leave blank to keep current: ");
            String teamInput = scanner.nextLine();

            if (teamInput.isEmpty()) {
                team = TeamDTO.modelToDTO(gamerEntity.getTeam());
            } else {
                int teamIndex = Integer.parseInt(teamInput);
                if (teamIndex > 0 && teamIndex <= teams.size()) {
                    Team selectedTeam = teams.get(teamIndex - 1);
                    team = new TeamDTO(selectedTeam.getId(), selectedTeam.getName(), selectedTeam.getRanking());
                } else {
                    logger.error("Invalid team index");
                    return null;
                }
            }
        }

        Gamer gamer = null;
        if (team == null) {
           gamer = gamerService.updateGamer(new GamerDTO(id,username, age));
        }else{
             gamer = gamerService.updateGamer(new GamerDTO(id, username, age, team));
        }

        if (gamer == null) {
            logger.error("Failed to update gamer");
            return null;
        } else {
            logger.info("Gamer updated successfully");
            return GamerDTO.modelToDTO(gamer);
        }
    }

    public void deleteGamer() {
        logger.info("Deleting a gamer");

        System.out.print("Enter the ID of the gamer: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Clear the newline character

        if (gamerService.deleteGamer(id)) {
            logger.info("Gamer deleted successfully");
        } else {
            logger.error("Failed to delete gamer");
        }
    }

    public void getAllGamers() {
        List<Gamer> gamers = gamerService.getAllGamers();

        if (gamers.isEmpty()) {
            logger.error("No gamers found");
        } else {
            logger.info("All gamers retrieved");
            gamers.stream().map(GamerDTO::modelToDTO).collect(Collectors.toList()).forEach(System.out::println);
        }
    }

    public GamerDTO getGamer() throws SQLException {
        logger.info("Getting a gamer");

        System.out.print("Enter the ID of the gamer: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Gamer gamer = gamerService.getGamer(id);

        if (gamer == null) {
            logger.error("Failed to get the gamer");
            return null;
        } else {
            logger.info("Successfully got the gamer");
            return GamerDTO.modelToDTO(gamer);
        }
    }
}
