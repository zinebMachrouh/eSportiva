package com.example.esportiva.controllers;

import com.example.esportiva.dto.TeamDTO;
import com.example.esportiva.dto.TournamentDTO;
import com.example.esportiva.models.Team;
import com.example.esportiva.models.Tournament;
import com.example.esportiva.services.interfaces.TeamService;
import com.example.esportiva.services.interfaces.TournamentService;
import com.example.esportiva.utils.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeamController {
    private final TeamService teamService;
    private final TournamentService tournamentService;

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    public TeamController(TeamService teamService, TournamentService tournamentService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
    }

    public TeamDTO addTeam() throws SQLException {
        logger.info("Adding a new team");

        String name = System.console().readLine("Enter the name of the team: ");
        while (InputValidation.handleString(name)) {
            name = System.console().readLine("Name cannot be empty. Enter the name of the team: ");
        }

        logger.info("Select the team's tournament");
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        for (int i = 0; i < tournaments.size(); i++) {
            System.out.println(i + 1 + ". " + tournaments.get(i).getTitle());
        }
        Integer tournamentIndex = Integer.parseInt(System.console().readLine("Enter the index of the tournament: "));
        Tournament tournament;
        if ( tournamentIndex < 0 || tournamentIndex >= tournaments.size()) {
            logger.error("Invalid tournament index");
            return null;
        }else if (String.valueOf(tournamentIndex).isEmpty()) {
            logger.error("Tournament index cannot be empty");
            return null;
        }else{
            tournament = tournaments.get(tournamentIndex-1);
            logger.info("Successfully selected the team's tournament");
        }

        TeamDTO team = new TeamDTO(name, 0);
        Team newTeam = teamService.addTeam(team);
        teamService.attachTournament(newTeam.getId(), tournament.getId());

        logger.info("Do you wanna add new gamers to the team? (yes/no)");
        String addGamer = System.console().readLine();
        if (addGamer.equals("yes")) {
            logger.info("Adding gamers to the team");
            while (addGamer.equals("yes")) {
                String gamerId = System.console().readLine("Enter the Id of the gamer: ");
                while (InputValidation.handleString(gamerId)) {
                    gamerId = System.console().readLine("Id cannot be empty. Enter the name of the gamer: ");
                }
                teamService.attachGamer(newTeam.getId(), UUID.fromString(gamerId));
                addGamer = System.console().readLine("Do you wanna add more gamers to the team? (yes/no)");
            }
        }


        if (newTeam == null) {
            logger.error("Failed to add a new team");
            return null;
        }else {
            logger.info("Successfully added a new team");
            return TeamDTO.modelToDTO(newTeam);
        }
    }

    public Team updateTeam() throws SQLException{
        logger.info("Updating a team");

        String id = System.console().readLine("Enter the id of the team: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the team: ");
        }

        Team teamEntity = teamService.getTeam(UUID.fromString(id));

        String name = System.console().readLine("Enter the name of the team: ");
        if (name.isEmpty()){
            name = teamEntity.getName();
        }else {
            while (InputValidation.handleString(name)) {
                name = System.console().readLine("Name cannot be empty. Enter the name of the team: ");
            }
        }

        Integer ranking = Integer.parseInt(System.console().readLine("Enter the ranking of the team: "));
        if (String.valueOf(ranking).isEmpty()){
            ranking = teamEntity.getRanking();
        }else {
            while (InputValidation.handleNumber(ranking)) {
                ranking = Integer.parseInt(System.console().readLine("Ranking cannot be empty. Enter the ranking of the team: "));
            }
        }

        logger.info("Do you wanna remove gamers from the team? (yes/no)");
        String removeGamer = System.console().readLine();
        if (removeGamer.equals("yes")) {
            logger.info("Removing gamers from the team");
            while (removeGamer.equals("yes")) {
                String gamerId = System.console().readLine("Enter the Id of the gamer: ");
                while (InputValidation.handleString(gamerId)) {
                    gamerId = System.console().readLine("Id cannot be empty. Enter the name of the gamer: ");
                }
                teamService.detachGamer(UUID.fromString(id), UUID.fromString(gamerId));
                removeGamer = System.console().readLine("Do you wanna remove more gamers from the team? (yes/no)");
            }
        }else {
            logger.info("Do you wanna add gamers to the team? (yes/no)");
            String addGamer = System.console().readLine();
            if (addGamer.equals("yes")) {
                logger.info("Adding gamers to the team");
                while (addGamer.equals("yes")) {
                    String gamerId = System.console().readLine("Enter the Id of the gamer: ");
                    while (InputValidation.handleString(gamerId)) {
                        gamerId = System.console().readLine("Id cannot be empty. Enter the name of the gamer: ");
                    }
                    teamService.attachGamer(UUID.fromString(id), UUID.fromString(gamerId));
                    addGamer = System.console().readLine("Do you wanna add more gamers to the team? (yes/no)");
                }
            }
        }


        TeamDTO team = new TeamDTO(name, ranking);
        Team updatedTeam = teamService.updateTeam(team);

        if (updatedTeam == null) {
            logger.error("Failed to update the team");
            return null;
        }else {
            logger.info("Successfully updated the team");
            return updatedTeam;
        }
    }

    public TeamDTO getTeam() throws SQLException {
        logger.info("Getting a team");

        String id = System.console().readLine("Enter the id of the team: ");
        while (InputValidation.handleUUID(id)) {
            id = System.console().readLine("Invalid id. Enter the id of the team: ");
        }

        Team team = teamService.getTeam(UUID.fromString(id));

        if (team == null) {
            logger.error("Failed to get the team");
            return null;
        }else {
            logger.info("Successfully got the team");
            return TeamDTO.modelToDTO(team);
        }
    }

    public List<TeamDTO> getAllTeams() throws SQLException {
        logger.info("Getting all teams");

        List<Team> teams = teamService.getAllTeams();

        if (teams == null) {
            logger.error("Failed to get all teams");
            return null;
        }else {
            logger.info("Successfully got all teams");
            return teams.stream().map(TeamDTO::modelToDTO).collect(Collectors.toList());
        }
    }
}
