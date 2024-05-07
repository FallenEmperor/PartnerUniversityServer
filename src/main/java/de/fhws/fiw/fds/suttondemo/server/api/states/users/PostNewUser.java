package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.security.AuthenticationProvider;

public class PostNewUser<R> extends AbstractPostState<R, User> {

    public PostNewUser(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.accessControl(this.suttonServletRequest, "ADMIN", "SUPERVISOR");
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getUserDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder<R> extends AbstractPostStateBuilder<R, User> {

        @Override
        public PostNewUser<R> build() {
            return new PostNewUser<>(this);
        }
    }
}
