package de.fhws.fiw.fds.suttondemo.server.api.security;

import de.fhws.fiw.fds.sutton.server.api.security.AbstractAuthenticationProvider;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;

import java.util.Optional;

public class AuthenticationProvider extends AbstractAuthenticationProvider {

    @Override
    protected Optional<User> loadUserFromDatabase(String name) {
        SingleModelResult<User> userFromDB = DaoFactory.getInstance().getUserDao().readUserByUsername(name);
        return Optional.ofNullable(userFromDB.getResult());
    }
}
