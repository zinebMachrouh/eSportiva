package com.example.esportiva.repositories;

import com.example.esportiva.models.Gamer;
import com.example.esportiva.repositories.interfaces.GamerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Collections;
import java.util.UUID;

public class GamerRepositoryImpl implements GamerRepository {

    private final EntityManagerFactory entityManagerFactory;

    public GamerRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Gamer addGamer(Gamer gamer) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(gamer);  // Persist the gamer into the database

            transaction.commit();
            return gamer;
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
    public Gamer updateGamer(Gamer gamer) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Gamer existingGamer = entityManager.find(Gamer.class, gamer.getId());
            if (existingGamer == null) {
                System.out.println("Gamer with ID " + gamer.getId() + " does not exist.");
                return null;
            }

            Gamer managedGamer = entityManager.merge(gamer);

            transaction.commit();
            return managedGamer;
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
    public boolean deleteGamer(Long id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Gamer gamer = entityManager.find(Gamer.class, id);  // Find the gamer by id
            if (gamer != null) {
                entityManager.remove(gamer);  // Remove the gamer if found
                transaction.commit();
                return true;
            }

            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Gamer getGamer(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Gamer.class, id);  // Find the gamer by id
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Gamer> getAllGamers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT g FROM Gamer g", Gamer.class).getResultList();  // Fetch all gamers
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }
}
