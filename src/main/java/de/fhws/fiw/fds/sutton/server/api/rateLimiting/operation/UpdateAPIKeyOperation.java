package de.fhws.fiw.fds.sutton.server.api.rateLimiting.operation;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.model.APIKey;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class UpdateAPIKeyOperation extends AbstractUpdateOperation<APIKey> {

    public UpdateAPIKeyOperation(EntityManagerFactory emf, APIKey modelToUpdate) {
        super(emf, modelToUpdate);
    }

}
