package de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;

public interface UserDaoHibernate extends IDatabaseAccessObjectHibernate<UserDB> {

    SingleModelHibernateResult<UserDB> readUserByUsername(final String username);
}
