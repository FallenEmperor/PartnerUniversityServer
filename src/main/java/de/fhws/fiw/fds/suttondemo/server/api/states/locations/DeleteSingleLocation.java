package de.fhws.fiw.fds.suttondemo.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonRelTypes;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonUri;

public class DeleteSingleLocation<R> extends AbstractDeleteState<R, Location> {

    public DeleteSingleLocation( final Builder<R> builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Location> loadModel( )
    {
        return DaoFactory.getInstance( ).getLocationDao( ).readById( this.modelIdToDelete );
    }

    @Override protected NoContentResult deleteModel( )
    {
        DaoFactory.getInstance( ).getPersonLocationDao( ).deleteRelationsToSecondary( this.modelIdToDelete );
        return DaoFactory.getInstance( ).getPersonDao( ).delete( this.modelIdToDelete );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( PersonUri.REL_PATH, PersonRelTypes.GET_ALL_PERSONS, getAcceptRequestHeader( ) );
    }

    public static class Builder<R> extends AbstractDeleteStateBuilder<R>
    {
        @Override public AbstractState<R, Void> build( )
        {
            return new DeleteSingleLocation<>( this );
        }
    }

}
