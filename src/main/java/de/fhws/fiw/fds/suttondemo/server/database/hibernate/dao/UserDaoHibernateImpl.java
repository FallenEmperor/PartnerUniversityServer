package de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.api.security.operations.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class UserDaoHibernateImpl implements UserDaoHibernate {

    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    public UserDaoHibernateImpl() {
    }

    @Override
    public NoContentResult create(UserDB model) {
        return new PersistUserOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<UserDB> readById(long id) {
        return new ReadUserByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<UserDB> readAll(SearchParameter searchParameter) {
        return new ReadAllUsersOperation(emf, searchParameter).start();
    }

    @Override
    public NoContentResult update(UserDB model) {
        return new UpdateUserOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new DeleteUserByIdOperation(emf, id).start();
    }

    @Override
    public SingleModelHibernateResult<UserDB> readUserByUsername(String username) {
        return new ReadUserByUsernameOperation(emf, username).start();
    }
}
