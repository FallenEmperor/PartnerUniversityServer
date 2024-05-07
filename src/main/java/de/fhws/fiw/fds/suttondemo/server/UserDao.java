package de.fhws.fiw.fds.suttondemo.server;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public interface UserDao extends IDatabaseAccessObject<User> {

    SingleModelResult<User> readUserByUsername(final String username);
}
