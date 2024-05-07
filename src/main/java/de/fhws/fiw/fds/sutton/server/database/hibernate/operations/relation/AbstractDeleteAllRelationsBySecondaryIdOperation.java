package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractDeleteAllRelationsBySecondaryIdOperation <
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation<PrimaryModel, SecondaryModel>>
        extends AbstractDatabaseOperation<SecondaryModel, NoContentResult> {

    private final Class<Relation> clazzOfRelation;
    private final long secondaryId;

    public AbstractDeleteAllRelationsBySecondaryIdOperation(EntityManagerFactory emf, Class<Relation> clazzOfRelation, long secondaryId) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.secondaryId = secondaryId;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Relation> delete = cb.createCriteriaDelete(this.clazzOfRelation);
        Root<Relation> rootEntry = delete.from(this.clazzOfRelation);

        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), this.secondaryId);
        delete.where(secondaryIdEquals);
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