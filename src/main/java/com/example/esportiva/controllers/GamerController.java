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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GamerController {
    private final GamerService gamerService;
    private final TeamService teamService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GamerController(GamerService gamerService, TeamService teamService) {
        this.gamerService = gamerService;
        this.teamService = teamService;
    }

    public GamerDTO addGamer() throws SQLException {
        logger.info("Adding a gamer");

        String username = System.console().readLine("Enter the username: ");
        while (InputValidation.handleString(username)) {
            username = System.console().readLine("Enter the username: ");
        }

        Integer age = Integer.parseInt(System.console().readLine("Enter the age: "));
        while (InputValidation.handleAge(age)) {
            age = Integer.parseInt(System.console().readLine("Enter the age: "));
        }

        logger.info("Select the team for the gamer");
        List<Team> teams = teamService.getAllTeams();
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("Team " + i + ": " + teams.get(i).getName());
        }
        Integer teamIndex = Integer.parseInt(System.console().readLine("Pick a Team: "));
        if (teamIndex < 0 || teamIndex >= teams.size()) {
            logger.error("Invalid team index");
        }
        TeamDTO team;
        if (String.valueOf(teamIndex).isEmpty()) {
            team = null;
        }else {
            Team selectedTeam = teams.get(teamIndex-1);
            team = new TeamDTO(selectedTeam.getId(), selectedTeam.getName(), selectedTeam.getRanking());
        }

        Gamer gamer = gamerService.addGamer(new GamerDTO(username, age, team));

        if (gamer == null) {
            logger.error("Failed to add gamer");
            return null;
        }else {
            logger.info("Gamer added successfully");
            return GamerDTO.modelToDTO(gamer);
        }
    }

    public GamerDTO updateGamer() throws SQLException{
        logger.info("Updating a gamer");

        String id = System.console().readLine("Enter the id of the gamer: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Enter the id of the gamer: ");
        }

        Gamer gamerEntity = gamerService.getGamer(UUID.fromString(id));

        String username = System.console().readLine("Enter the username: ");
        if (username.isEmpty()){
            username = gamerEntity.getUsername();
        }else {
            while (InputValidation.handleString(username)) {
                username = System.console().readLine("Enter the username: ");
            }
        }

        Integer age = Integer.parseInt(System.console().readLine("Enter the age: "));
        if (String.valueOf(age).isEmpty()) {
            age = gamerEntity.getAge();
        }else {
            while (InputValidation.handleAge(age)) {
                age = Integer.parseInt(System.console().readLine("Enter the age: "));
            }
        }

        logger.info("Select the team for the gamer");
        List<Team> teams = teamService.getAllTeams();
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("Team " + i + ": " + teams.get(i).getName());
        }
        Integer teamIndex = Integer.parseInt(System.console().readLine("Pick a Team: "));
        if (teamIndex < 0 || teamIndex >= teams.size()) {
            logger.error("Invalid team index");
        }
        TeamDTO team;
        if (String.valueOf(teamIndex).isEmpty()) {
            team = TeamDTO.modelToDTO(gamerEntity.getTeam());
        }else {
            Team selectedTeam = teams.get(teamIndex-1);
            team = new TeamDTO(selectedTeam.getId(), selectedTeam.getName(), selectedTeam.getRanking());
        }

        Gamer gamer = gamerService.updateGamer(new GamerDTO(UUID.fromString(id), username, age, team));

        if (gamer == null) {
            logger.error("Failed to update gamer");
            return null;
        }else {
            logger.info("Gamer updated successfully");
            return GamerDTO.modelToDTO(gamer);
        }
    }

    public void deleteGamer(){
        logger.info("Deleting a gamer");

        String id = System.console().readLine("Enter the id of the gamer: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Enter the id of the gamer: ");
        }

        if (gamerService.deleteGamer(UUID.fromString(id))) {
            logger.info("Gamer deleted successfully");
        }else {
            logger.error("Failed to delete gamer");
        }
    }

    public List<GamerDTO> getAllUsers(){
        List<Gamer> gamers = gamerService.getAllGamers();

        if (gamers.isEmpty()) {
            logger.error("No gamers found");
            return null;
        }else {
            logger.info("All gamers");
            return gamers.stream().map(GamerDTO::modelToDTO).collect(Collectors.toList());
        }
    }
}
