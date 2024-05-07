package de.fhws.fiw.fds.sutton.server.api.security.operations;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class PersistUserOperation extends AbstractPersistOperation<UserDB> {

    public PersistUserOperation(EntityManagerFactory emf, UserDB modelToPersist) {
        super(emf, modelToPersist);
    }
}
