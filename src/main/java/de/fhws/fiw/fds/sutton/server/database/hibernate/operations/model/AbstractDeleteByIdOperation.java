package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;

public abstract class AbstractDeleteByIdOperation<T extends AbstractDBModel>
        extends AbstractDatabaseOperation<T, NoContentResult> {

    private long idToDelete;
    private Class<T> clazz;

    public AbstractDeleteByIdOperation(EntityManagerFactory emf, Class<T> clazz, long idToDelete) {
        super(emf);
        this.clazz = clazz;
        this.idToDelete = idToDelete;
    }

    @Override
    protected NoContentResult run() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> delete = cb.createCriteriaDelete(this.clazz);
        Root<T> rootEntry = delete.from(this.clazz);
        delete.where(cb.equal(rootEntry.get("id"), this.idToDelete));
        em.createQuery(delete).executeUpdate();

        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
