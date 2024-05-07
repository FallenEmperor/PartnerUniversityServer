package de.fhws.fiw.fds.sutton.server.api.security.operations;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ReadUserByIdOperation extends AbstractReadByIdOperation<UserDB> {

    public ReadUserByIdOperation(EntityManagerFactory emf, long idToLoad) {
        super(emf, UserDB.class, idToLoad);
    }
}
