package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.security.AuthenticationProvider;

public class DeleteUser<R> extends AbstractDeleteState<R, User> {

    public DeleteUser(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.accessControl(this.suttonServletRequest, "ADMIN");
    }

    @Override
    protected SingleModelResult<User> loadModel() {
        return DaoFactory.getInstance().getUserDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getUserDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder<R> extends AbstractDeleteStateBuilder<R> {

        @Override
        public DeleteUser<R> build() {
            return new DeleteUser<>(this);
        }
    }
}
