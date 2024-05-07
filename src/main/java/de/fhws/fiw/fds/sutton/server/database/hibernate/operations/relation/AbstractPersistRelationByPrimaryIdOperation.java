package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractPersistRelationByPrimaryIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation<PrimaryModel, SecondaryModel>>
        extends AbstractDatabaseOperation<SecondaryModel, NoContentResult> {

    private final Class<Relation> clazzOfRelation;
    private final Class<PrimaryModel> clazzOfPrimaryModel;
    private final long primaryId;
    private final SecondaryModel secondaryModel;

    public AbstractPersistRelationByPrimaryIdOperation(EntityManagerFactory emf, Class<Relation> clazzOfRelation, Class<PrimaryModel> clazzOfPrimaryModel, long primaryId, SecondaryModel secondaryModel) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.clazzOfPrimaryModel = clazzOfPrimaryModel;
        this.primaryId = primaryId;
        this.secondaryModel = secondaryModel;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        PrimaryModel primaryModel = this.em.find(clazzOfPrimaryModel, primaryId);
        this.em.persist(secondaryModel);
        AbstractDBRelation<PrimaryModel, SecondaryModel> relation = clazzOfRelation.getDeclaredConstructor().newInstance();
        relation.setPrimaryModel(primaryModel);
        relation.setSecondaryModel(secondaryModel);
        this.em.merge(relation); // merge is needed because of detached entity exception
        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
