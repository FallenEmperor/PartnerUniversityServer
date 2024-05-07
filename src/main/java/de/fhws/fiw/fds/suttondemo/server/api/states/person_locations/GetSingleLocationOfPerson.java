package de.fhws.fiw.fds.suttondemo.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class GetSingleLocationOfPerson<R> extends AbstractGetRelationState<R, Location> {

    public GetSingleLocationOfPerson( final Builder<R> builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String modelFromDBEtag = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(modelFromDBEtag);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override protected SingleModelResult<Location> loadModel( )
    {
        SingleModelResult<Location> location = DaoFactory.getInstance( ).getLocationDao( ).readById( this.requestedId );
        if(isPersonLinkedToThisLocation()) {
            location.getResult().setPrimaryId(this.primaryId);
        }
        return location;
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( PersonLocationUri.REL_PATH_SHOW_ONLY_LINKED,
                PersonLocationRelTypes.GET_ALL_LINKED_LOCATIONS,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( isPersonLinkedToThisLocation( ) )
        {
            addLink( PersonLocationUri.REL_PATH_ID,
                    PersonLocationRelTypes.UPDATE_SINGLE_LOCATION,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            addLink( PersonLocationUri.REL_PATH_ID,
                    PersonLocationRelTypes.DELETE_LINK_FROM_PERSON_TO_LOCATION,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
        else
        {
            addLink( PersonLocationUri.REL_PATH_ID,
                    PersonLocationRelTypes.CREATE_LINK_FROM_PERSON_TO_LOCATION,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
    }

    private boolean isPersonLinkedToThisLocation( )
    {
        return !DaoFactory.getInstance( )
                .getPersonLocationDao( )
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }

    public static class Builder<R> extends AbstractGetRelationStateBuilder<R, Location>
    {
        @Override
        public AbstractState<R, Location> build( )
        {
            return new GetSingleLocationOfPerson<>( this );
        }
    }

}
