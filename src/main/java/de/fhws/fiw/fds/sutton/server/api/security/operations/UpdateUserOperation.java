package de.fhws.fiw.fds.sutton.server.api.security.operations;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class UpdateUserOperation extends AbstractUpdateOperation<UserDB> {

    public UpdateUserOperation(EntityManagerFactory emf, UserDB modelToUpdate) {
        super(emf, modelToUpdate);
    }
}
