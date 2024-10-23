package com.example.esportiva.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            // Create the EntityManagerFactory from persistence.xml
            return Persistence.createEntityManagerFactory("eSportivaPU");
        } catch (Throwable ex) {
            // Log the exception
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        System.out.println("Creating EntityManagerFactory");
        return entityManagerFactory;
    }
}
