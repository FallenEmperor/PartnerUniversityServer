package de.fhws.fiw.fds.sutton.server.database.hibernate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Use this Interface for every connection to your DataBase
 */
public interface IDatabaseConnection {

    /**
     * The {@link EntityManagerFactory} for your Database
     */
    EntityManagerFactory SUTTON_EMF = //
            Persistence.createEntityManagerFactory("de.fhws.fiw.fds.sutton");

}
