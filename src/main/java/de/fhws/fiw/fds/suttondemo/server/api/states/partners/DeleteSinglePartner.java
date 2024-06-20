package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class DeleteSinglePartner extends AbstractDeleteState<Response, PartnerUniversity> {

        public DeleteSinglePartner(ServiceContext serviceContext, long modelIdToDelete) {
        super(serviceContext, modelIdToDelete);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPartnerDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

        addLink(PartnerUri.REL_PATH, PartnerRelTypes.GET_ALL_PARTNERS, getAcceptRequestHeader());

    }

}
