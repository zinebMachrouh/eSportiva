package com.example.esportiva.repositories;

import com.example.esportiva.models.Gamer;
import com.example.esportiva.models.Team;
import com.example.esportiva.repositories.interfaces.TeamRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class TeamRepositoryImpl implements TeamRepository {

    private final EntityManagerFactory entityManagerFactory;

    public TeamRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Team addTeam(Team team) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team managedTeam = entityManager.merge(team);

            transaction.commit();
            return managedTeam;
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
    public Team updateTeam(Team team) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team managedTeam = entityManager.merge(team);

            transaction.commit();
            return managedTeam;
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
    public Team getTeam(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Team.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Team> getAllTeams() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Team attachGamer(UUID teamId, UUID gamerId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Gamer gamer = entityManager.find(Gamer.class, gamerId);
            if (team != null && gamer != null) {
                team.addGamer(gamer);
                entityManager.merge(team);
            }

            transaction.commit();
            return team;
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
    public Team detachGamer(UUID teamId, UUID gamerId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Gamer gamer = entityManager.find(Gamer.class, gamerId);
            if (team != null && gamer != null) {
                team.removeGamer(gamer);
                entityManager.merge(team);
            }

            transaction.commit();
            return team;
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
}
