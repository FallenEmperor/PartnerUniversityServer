package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractDeleteRelationByIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation<PrimaryModel, SecondaryModel>>
        extends AbstractDatabaseOperation<SecondaryModel, NoContentResult> {

    private final Class<Relation> clazzOfRelation;
    private final long primaryId;
    private final long secondaryId;

    public AbstractDeleteRelationByIdOperation(EntityManagerFactory emf, Class<Relation> clazzOfRelation, long primaryId, long secondaryId) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.primaryId = primaryId;
        this.secondaryId = secondaryId;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Relation> delete = cb.createCriteriaDelete(this.clazzOfRelation);
        Root<Relation> rootEntry = delete.from(this.clazzOfRelation);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);
        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), this.secondaryId);
        delete.where(primaryIdEquals, secondaryIdEquals);
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
