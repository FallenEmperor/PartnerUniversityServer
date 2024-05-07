package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractUpdateRelationByPrimaryIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation<PrimaryModel, SecondaryModel>>
        extends AbstractDatabaseOperation<SecondaryModel, NoContentResult> {

    private final Class<Relation> clazzOfRelation;
    private final Class<PrimaryModel> clazzOfPrimaryModel;
    private final long primaryId;
    private final SecondaryModel secondaryModelToUpdate;

    public AbstractUpdateRelationByPrimaryIdOperation(EntityManagerFactory emf, Class<Relation> clazzOfRelation, Class<PrimaryModel> clazzOfPrimaryModel, long primaryId, SecondaryModel secondaryModelToUpdate) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.clazzOfPrimaryModel = clazzOfPrimaryModel;
        this.primaryId = primaryId;
        this.secondaryModelToUpdate = secondaryModelToUpdate;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        em.merge(secondaryModelToUpdate);

        // Only persist new relation, if there is none existent
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relation> find = cb.createQuery(this.clazzOfRelation);
        Root<Relation> rootEntry = find.from(this.clazzOfRelation);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);
        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), secondaryModelToUpdate.getId());
        find.where(primaryIdEquals, secondaryIdEquals);

        Relation relationOnDB = em.createQuery(find).getResultStream().findFirst().orElse(null);

        if(relationOnDB == null){
            PrimaryModel primaryResource = this.em.find(clazzOfPrimaryModel, primaryId);
            AbstractDBRelation<PrimaryModel, SecondaryModel> relation = clazzOfRelation.getDeclaredConstructor().newInstance();
            relation.setPrimaryModel(primaryResource);
            relation.setSecondaryModel(secondaryModelToUpdate);
            this.em.merge(relation); // merge is needed because of detached entity exception
        }

        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
