package de.fhws.fiw.fds.suttondemo.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class PutSingleLocationOfPerson<R> extends AbstractPutRelationState<R, Location> {

    public PutSingleLocationOfPerson(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

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
    protected SingleModelResult<Location> loadModel() {
        return DaoFactory.getInstance().getLocationDao().readById(this.requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPersonLocationDao().update(this.primaryId, this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonLocationUri.REL_PATH_ID,
                PersonLocationRelTypes.GET_SINGLE_LOCATION,
                getAcceptRequestHeader(),
                this.primaryId, this.requestedId);
    }

    public static class Builder<R> extends AbstractPutRelationStateBuilder<R, Location> {
        @Override
        public AbstractState<R, Void> build() {
            return new PutSingleLocationOfPerson<>(this);
        }
    }

}
