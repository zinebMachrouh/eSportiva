package com.example.esportiva;

import com.example.esportiva.models.Game;
import com.example.esportiva.models.enums.GameDifficulty;
import com.example.esportiva.repositories.interfaces.GameRepository;
import com.example.esportiva.utils.HibernateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

public class ESportiva {
    public  static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GameRepository gameRepository = (GameRepository) context.getBean("gameRepository");


    }
}
