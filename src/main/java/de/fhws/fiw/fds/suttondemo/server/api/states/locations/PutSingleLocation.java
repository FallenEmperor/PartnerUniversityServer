package de.fhws.fiw.fds.suttondemo.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class PutSingleLocation<R> extends AbstractPutState<R, Location> {

    public PutSingleLocation( final Builder<R> builder )
    {
        super( builder );
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

    @Override protected SingleModelResult<Location> loadModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).readById( this.modelToUpdate.getId( ) );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).update( this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( LocationUri.REL_PATH_ID, LocationRelTypes.GET_SINGLE_LOCATION, getAcceptRequestHeader( ),
                this.modelToUpdate.getId( ) );
    }

    public static class Builder<R> extends AbstractPutStateBuilder<R, Location>
    {
        @Override public AbstractState<R, Void> build( )
        {
            return new PutSingleLocation<>( this );
        }
    }

}
