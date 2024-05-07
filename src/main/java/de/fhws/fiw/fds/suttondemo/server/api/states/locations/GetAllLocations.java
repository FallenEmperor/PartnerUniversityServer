package de.fhws.fiw.fds.suttondemo.server.api.states.locations;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

public class GetAllLocations extends AbstractGetCollectionState<Response, Location> {
    public GetAllLocations(ServiceContext serviceContext, AbstractQuery<Response, Location> query) {
        super(serviceContext, query);
    }


    @Override
    protected void defineTransitionLinks() {
        addLink(LocationUri.REL_PATH, LocationRelTypes.CREATE_LOCATION, getAcceptRequestHeader());
    }

    public static class AllLocations<R> extends AbstractQuery<R, Location> {
        @Override
        protected CollectionModelResult<Location> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getLocationDao().readAll();
        }
    }
}
