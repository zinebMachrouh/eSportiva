package com.example.esportiva;

import com.example.esportiva.controllers.GameController;
import com.example.esportiva.controllers.GamerController;
import com.example.esportiva.controllers.TeamController;
import com.example.esportiva.controllers.TournamentController;
import com.example.esportiva.presentation.CoonsoleUI;
import com.example.esportiva.repositories.interfaces.GameRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class
ESportiva {
    public  static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        GameController gameController = context.getBean(GameController.class);
        GamerController gamerController = context.getBean(GamerController.class);
        TeamController teamController = context.getBean(TeamController.class);
        TournamentController tournamentController = context.getBean(TournamentController.class);

        CoonsoleUI coonsoleUI = new CoonsoleUI(gameController, gamerController, teamController, tournamentController);
        try {
            coonsoleUI.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
