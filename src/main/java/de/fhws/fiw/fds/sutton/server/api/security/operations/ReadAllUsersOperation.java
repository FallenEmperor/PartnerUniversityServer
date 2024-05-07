package de.fhws.fiw.fds.sutton.server.api.security.operations;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import jakarta.persistence.EntityManagerFactory;

public class ReadAllUsersOperation extends AbstractReadAllOperation<UserDB> {

    public ReadAllUsersOperation(EntityManagerFactory emf, SearchParameter searchParameter) {
        super(emf, UserDB.class, searchParameter);
    }
}
