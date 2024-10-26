package com.example.esportiva;

import com.example.esportiva.repositories.interfaces.GameRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class
ESportiva {
    public  static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GameRepository gameRepository = (GameRepository) context.getBean("gameRepository");


    }
}
