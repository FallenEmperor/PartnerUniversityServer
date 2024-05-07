package de.fhws.fiw.fds.suttondemo.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByLocationName;

import java.util.Collection;

public class GetAllLocationsOfPerson<R> extends AbstractGetCollectionRelationState<R, Location> {
    public GetAllLocationsOfPerson(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
        QueryByLocationName<R> theQuery = (QueryByLocationName<R>) this.query;
        int waitingTime = theQuery.getWaitingTime();

        try {
            Thread.sleep(waitingTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonLocationUri.REL_PATH,
                PersonLocationRelTypes.CREATE_LOCATION,
                getAcceptRequestHeader(),
                this.primaryId);


        addLink(PersonLocationUri.REL_PATH_SHOW_ALL,
                PersonLocationRelTypes.GET_ALL_LOCATIONS,
                getAcceptRequestHeader(),
                this.primaryId);

    }

    public static class Builder<R> extends AbstractGetCollectionRelationStateBuilder<R, Location> {
        @Override
        public AbstractState<R, Collection<Location>> build() {
            return new GetAllLocationsOfPerson<R>(this);
        }
    }
}
