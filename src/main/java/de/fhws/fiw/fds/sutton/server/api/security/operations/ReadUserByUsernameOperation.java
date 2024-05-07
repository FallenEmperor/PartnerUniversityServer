package de.fhws.fiw.fds.sutton.server.api.security.operations;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
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

public class ReadUserByUsernameOperation extends AbstractDatabaseOperation<UserDB,
        SingleModelHibernateResult<UserDB>> {

    private final String username;

    public ReadUserByUsernameOperation(EntityManagerFactory emf, String username) {
        super(emf);
        this.username = username;
    }


    @Override
    protected SingleModelHibernateResult<UserDB> run() throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserDB> find = cb.createQuery(UserDB.class);
        Root<UserDB> rootEntry = find.from(UserDB.class);

        Predicate apiKeyEquals = cb.equal(rootEntry.get(SuttonColumnConstants.USER_NAME), this.username);
        find.where(apiKeyEquals);
        TypedQuery<UserDB> findQuery = em.createQuery(find);

        return new SingleModelHibernateResult<>(findQuery.getResultStream().findFirst().orElse(null));
    }

    @Override
    protected SingleModelHibernateResult<UserDB> errorResult() {
        final SingleModelHibernateResult<UserDB> returnValue = new SingleModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
