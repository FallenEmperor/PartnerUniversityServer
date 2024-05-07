package de.fhws.fiw.fds.sutton.server.api.rateLimiting.operation;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.model.APIKey;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.InvocationTargetException;

public class ReadAPIKeyOperation extends AbstractDatabaseOperation<APIKey, SingleModelHibernateResult<APIKey>> {

    private final String apiKey;

    public ReadAPIKeyOperation(EntityManagerFactory emf, String apiKey) {
        super(emf);
        this.apiKey = apiKey;
    }

    @Override
    protected SingleModelHibernateResult<APIKey> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<APIKey> find = cb.createQuery(APIKey.class);
        Root<APIKey> rootEntry = find.from(APIKey.class);

        Predicate apiKeyEquals = cb.equal(rootEntry.get(SuttonColumnConstants.API_KEY), this.apiKey);
        find.where(apiKeyEquals);
        TypedQuery<APIKey> findQuery = em.createQuery(find);

        return new SingleModelHibernateResult<>(findQuery.getResultStream().findFirst().orElse(null));
    }

    @Override
    protected SingleModelHibernateResult<APIKey> errorResult() {
        final SingleModelHibernateResult<APIKey> returnValue = new SingleModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
