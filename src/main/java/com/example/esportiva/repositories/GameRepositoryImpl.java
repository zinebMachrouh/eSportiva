package com.example.esportiva.repositories;

import com.example.esportiva.models.Game;
import com.example.esportiva.repositories.interfaces.GameRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class GameRepositoryImpl implements GameRepository {
    private final EntityManagerFactory entityManagerFactory;

    public GameRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Game addGame(Game game) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game managedGame = entityManager.merge(game);

            transaction.commit();
            return managedGame;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Game updateGame(Game game) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game managedGame = entityManager.merge(game);

            transaction.commit();
            return managedGame;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Game getGame(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Game.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Game> getAllGames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
