package de.fhws.fiw.fds.sutton.server.api.rateLimiting.operation;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.model.APIKey;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class PersistAPIKeyOperation extends AbstractPersistOperation<APIKey> {

    public PersistAPIKeyOperation(EntityManagerFactory emf, APIKey modelToPersist) {
        super(emf, modelToPersist);
    }

}
