package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PutSingleModuleOfPartner extends AbstractPutRelationState<Response, Module> {

    public PutSingleModuleOfPartner(ServiceContext serviceContext, long primaryId, long requestedId, Module modelToUpdate) {
        super(serviceContext, primaryId, requestedId, modelToUpdate);
        this.suttonResponse = new JerseyResponse<>();
        this.modelToUpdate.setPrimaryId(primaryId);
        this.modelToUpdate.setId(requestedId);
    }


    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<Module> loadModel() {
        return DaoFactory.getInstance().getPartnerModuleDao().readById(this.modelToUpdate.getPrimaryId(), this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPartnerModuleDao().update(this.modelToUpdate.getPrimaryId(), this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to single module (just updated)

        addLink(PartnerModuleUri.REL_PATH_ID,
                PartnerModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.modelToUpdate.getPrimaryId(), this.modelToUpdate.getId());
    }

}
