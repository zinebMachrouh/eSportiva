package com.example.esportiva.repositories;

import com.example.esportiva.models.Gamer;
import com.example.esportiva.models.Team;
import com.example.esportiva.models.Tournament;
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

            if (team.getId() == null) {
                throw new IllegalArgumentException("Team ID must be provided for update.");
            }

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
    public Team getTeam(Long id) {
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
    public Team attachGamer(Long teamId, Long gamerId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Gamer gamer = entityManager.find(Gamer.class, gamerId);
            if (team != null && gamer != null) {
                gamer.setTeam(team);

                if (!team.getGamers().contains(gamer)) {
                    team.addGamer(gamer);
                }

                entityManager.merge(gamer);
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
    public Team detachGamer(Long teamId, Long gamerId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Gamer gamer = entityManager.find(Gamer.class, gamerId);
            if (team != null && gamer != null) {
                team.removeGamer(gamer);
                gamer.setTeam(null);

                entityManager.merge(gamer);
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
    public Team attachTournament(Long teamId, Long tournamentId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            if (team != null && tournament != null) {
                if (!team.getTournaments().contains(tournament)) {
                    team.addTournament(tournament);
                    entityManager.merge(team);
                }
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
    public Team detachTournament(Long teamId, Long tournamentId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team team = entityManager.find(Team.class, teamId);
            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            if (team != null && tournament != null) {
                team.removeTournament(tournament);
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
