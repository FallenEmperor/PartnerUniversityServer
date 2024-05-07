package de.fhws.fiw.fds.suttondemo.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class PostNewLocationOfPerson<R> extends AbstractPostRelationState<R, Location> {

    public PostNewLocationOfPerson( final Builder<R> builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getPersonLocationDao( ).create( this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }

    public static class Builder<R> extends AbstractPostRelationStateBuilder<R, Location>
    {
        @Override public AbstractState<R, Void> build( )
        {
            return new PostNewLocationOfPerson<>( this );
        }
    }

}
