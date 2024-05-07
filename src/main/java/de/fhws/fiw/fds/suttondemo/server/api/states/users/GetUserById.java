package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.security.AuthenticationProvider;

public class GetUserById<R> extends AbstractGetState<R, User> {

    public GetUserById(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.accessControl(this.suttonServletRequest, "ADMIN", "SUPERVISOR", "USER");
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String modelEtag = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(modelEtag);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<User> loadModel() {
        return DaoFactory.getInstance().getUserDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UserUri.REL_PATH_ID, UserRelTypes.UPDATE_USER, getAcceptRequestHeader(), this.requestedId);
        addLink(UserUri.REL_PATH_ID, UserRelTypes.DELETE_USER, getAcceptRequestHeader(), this.requestedId);
        addLink(UserUri.REL_PATH, UserRelTypes.GET_All_USERS, getAcceptRequestHeader(), this.requestedId);
    }

    public static class Builder<R> extends AbstractGetStateBuilder<R, User> {

        @Override
        public GetUserById<R> build() {
            return new GetUserById<>(this);
        }
    }
}
