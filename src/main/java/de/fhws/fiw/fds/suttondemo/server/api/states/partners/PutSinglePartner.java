package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PutSinglePartner extends AbstractPutState<Response, PartnerUniversity> {

    public PutSinglePartner(ServiceContext serviceContext, long requestedId, PartnerUniversity modelToUpdate) {
        super(serviceContext, requestedId, modelToUpdate);
        this.suttonResponse = new JerseyResponse<>();
        modelToUpdate.setId(requestedId);
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPartnerDao().update(this.modelToUpdate);
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to see the updated partner
        
        addLink(PartnerUri.REL_PATH_ID, PartnerRelTypes.GET_SINGLE_PARTNER, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }

}
