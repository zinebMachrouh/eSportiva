package com.example.esportiva.repositories;

import com.example.esportiva.models.Tournament;
import com.example.esportiva.models.Game;
import com.example.esportiva.models.Team;
import com.example.esportiva.repositories.interfaces.TournamentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class TournamentRepositoryImpl implements TournamentRepository {

    private final EntityManagerFactory entityManagerFactory;

    public TournamentRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Tournament addTournament(Tournament tournament) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament managedTournament = entityManager.merge(tournament);

            transaction.commit();
            return managedTournament;
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
    public Tournament updateTournament(Tournament tournament) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament managedTournament = entityManager.merge(tournament);

            transaction.commit();
            return managedTournament;
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
    public Tournament getTournament(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Tournament.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tournament attachTeam(UUID tournamentId, UUID teamId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            Team team = entityManager.find(Team.class, teamId);
            if (tournament != null && team != null) {
                tournament.addTeam(team);
                entityManager.merge(tournament);
            }

            transaction.commit();
            return tournament;
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
    public Tournament detachTeam(UUID tournamentId, UUID teamId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            Team team = entityManager.find(Team.class, teamId);
            if (tournament != null && team != null) {
                tournament.removeTeam(team);
                entityManager.merge(tournament);
            }

            transaction.commit();
            return tournament;
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
    public Tournament attachGame(UUID tournamentId, UUID gameId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            Game game = entityManager.find(Game.class, gameId);
            if (tournament != null && game != null) {
                tournament.setGame(game);
                entityManager.merge(tournament);
            }

            transaction.commit();
            return tournament;
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
    public Tournament detachGame(UUID tournamentId, UUID gameId) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Tournament tournament = entityManager.find(Tournament.class, tournamentId);
            if (tournament != null && tournament.getGame() != null && tournament.getGame().getId().equals(gameId)) {
                tournament.setGame(null);
                entityManager.merge(tournament);
            }

            transaction.commit();
            return tournament;
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
    public List<Tournament> getAllTournaments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tournament> getUpcomingTournaments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Tournament t WHERE t.startDate > CURRENT_DATE", Tournament.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
