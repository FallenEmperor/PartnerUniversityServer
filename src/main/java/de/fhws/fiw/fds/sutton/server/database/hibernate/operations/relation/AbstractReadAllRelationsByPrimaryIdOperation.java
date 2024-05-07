package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractReadAllRelationsByPrimaryIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDBRelation>
        extends AbstractDatabaseOperation<SecondaryModel, CollectionModelHibernateResult<SecondaryModel>> {

    private final Class<Relation> clazzOfRelation;
    private final long primaryId;
    private final SearchParameter searchParameter;

    public AbstractReadAllRelationsByPrimaryIdOperation(EntityManagerFactory emf,
                                                        Class<Relation> clazzOfRelation,
                                                        long primaryId,
                                                        SearchParameter searchParameter) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.primaryId = primaryId;
        this.searchParameter = searchParameter;
    }

    @Override
    protected CollectionModelHibernateResult<SecondaryModel> run() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relation> find = cb.createQuery(this.clazzOfRelation);
        Root<Relation> rootEntry = find.from(this.clazzOfRelation);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);
        find.where(primaryIdEquals);
        TypedQuery<Relation> findQuery = em.createQuery(find);
        List<SecondaryModel> results = findQuery
                .setHint("org.hibernate.cacheable", true)
                .setFirstResult(this.searchParameter.getOffset())
                .setMaxResults(this.searchParameter.getSize())
                .getResultList()
                .stream()
                .map(r -> (SecondaryModel) r.getSecondaryModel())
                .collect(Collectors.toList());
        return new CollectionModelHibernateResult<>(results);
    }

    @Override
    protected CollectionModelHibernateResult<SecondaryModel> errorResult() {
        final CollectionModelHibernateResult<SecondaryModel> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
