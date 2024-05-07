package de.fhws.fiw.fds.suttondemo.server.api.states.locations;


import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class PostNewLocation<R> extends AbstractPostState<R, Location>
{
	public PostNewLocation(final Builder<R> builder )
	{
		super( builder );
	}

	@Override
	protected void authorizeRequest() {

	}

	@Override protected NoContentResult saveModel( )
	{
		return DaoFactory.getInstance( ).getLocationDao( ).create( this.modelToStore );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class Builder<R> extends AbstractPostStateBuilder<R, Location>
	{
		@Override public AbstractState<R, Void> build( )
		{
			return new PostNewLocation<>( this );
		}
	}
}
