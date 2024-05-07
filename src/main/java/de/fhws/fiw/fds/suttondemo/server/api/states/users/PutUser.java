package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.security.AuthenticationProvider;

public class PutUser<R> extends AbstractPutState<R, User> {

    public PutUser(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.accessControl(this.suttonServletRequest, "ADMIN", "SUPERVISOR");
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<User> loadModel() {
        return DaoFactory.getInstance().getUserDao().readById(this.requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getUserDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UserUri.REL_PATH_ID, UserRelTypes.GET_USER_BY_ID, getAcceptRequestHeader(), this.modelToUpdate.getId());
    }

    public static class Builder<R> extends AbstractPutStateBuilder<R, User> {

        @Override
        public PutUser<R> build() {
            return new PutUser<>(this);
        }
    }
}
