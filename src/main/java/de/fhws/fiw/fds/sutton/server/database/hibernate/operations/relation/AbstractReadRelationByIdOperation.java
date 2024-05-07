package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public abstract class AbstractReadRelationByIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation>
        extends AbstractDatabaseOperation<SecondaryModel, SingleModelHibernateResult<SecondaryModel>> {

    private final Class<Relation> clazzOfRelation;
    private final long primaryId;
    private final long secondaryId;

    public AbstractReadRelationByIdOperation(EntityManagerFactory emf, Class<Relation> clazzOfRelation, long primaryId, long secondaryId) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.primaryId = primaryId;
        this.secondaryId = secondaryId;
    }


    @Override
    protected SingleModelHibernateResult<SecondaryModel> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relation> find = cb.createQuery(this.clazzOfRelation);
        Root<Relation> rootEntry = find.from(this.clazzOfRelation);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);
        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), this.secondaryId);
        find.where(primaryIdEquals, secondaryIdEquals);

        Optional<Relation> result = em.createQuery(find).getResultStream().findFirst();
        return result.map(relation -> new SingleModelHibernateResult<>((SecondaryModel) relation.getSecondaryModel()))
                .orElseGet(SingleModelHibernateResult::new);
    }

    @Override
    protected SingleModelHibernateResult<SecondaryModel> errorResult() {
        final SingleModelHibernateResult<SecondaryModel> returnValue = new SingleModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
