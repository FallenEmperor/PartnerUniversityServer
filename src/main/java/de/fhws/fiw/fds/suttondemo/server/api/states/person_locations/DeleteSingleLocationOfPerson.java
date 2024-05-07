package de.fhws.fiw.fds.suttondemo.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class DeleteSingleLocationOfPerson<R> extends AbstractDeleteRelationState<R, Location> {

    public DeleteSingleLocationOfPerson(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Location> loadModel() {
        return DaoFactory.getInstance().getPersonLocationDao().readById(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPersonLocationDao().deleteRelation(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonLocationUri.REL_PATH,
                PersonLocationRelTypes.GET_ALL_LINKED_LOCATIONS,
                getAcceptRequestHeader(),
                this.primaryId);
    }

    public static class Builder<R> extends AbstractDeleteRelationStateBuilder<R> {
        @Override
        public AbstractState<R, Void> build() {
            return new DeleteSingleLocationOfPerson<R>(this);
        }
    }

}
