package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;

public abstract class AbstractReadByIdOperation<T extends AbstractDBModel>
        extends AbstractDatabaseOperation<T, SingleModelHibernateResult> {

    private long idToLoad;
    private Class<T> clazz;

    public AbstractReadByIdOperation(EntityManagerFactory emf, Class<T> type, long idToLoad) {
        super(emf);
        this.clazz = type;
        this.idToLoad = idToLoad;
    }

    @Override
    protected SingleModelHibernateResult<T> run() {
        final T result = this.em.find(this.clazz, this.idToLoad);

        if (result != null) {
            return new SingleModelHibernateResult<>(result);
        } else {
            return new SingleModelHibernateResult<>();
        }
    }

    @Override
    protected SingleModelHibernateResult<T> errorResult() {
        final SingleModelHibernateResult<T> returnValue = new SingleModelHibernateResult<T>();
        returnValue.setError();
        return returnValue;
    }

}
