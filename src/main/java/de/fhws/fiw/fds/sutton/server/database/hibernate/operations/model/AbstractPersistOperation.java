package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public abstract class AbstractPersistOperation<T extends AbstractDBModel>
        extends AbstractDatabaseOperation<T, NoContentResult> {

    private T modelToPersist;

    protected AbstractPersistOperation(EntityManagerFactory emf, T modelToPersist) {
        super(emf);
        this.modelToPersist = modelToPersist;
    }

    @Override
    protected NoContentResult run() {
        this.em.persist(this.modelToPersist);
        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
