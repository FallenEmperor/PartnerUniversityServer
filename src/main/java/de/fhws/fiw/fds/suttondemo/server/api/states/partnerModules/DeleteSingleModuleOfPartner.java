package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class DeleteSingleModuleOfPartner extends AbstractDeleteRelationState<Response, Module> {

        public DeleteSingleModuleOfPartner(ServiceContext serviceContext, long modelIdToDelete, long primaryId) {
        super(serviceContext, modelIdToDelete, primaryId);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected SingleModelResult<Module> loadModel() {
        return DaoFactory.getInstance().getPartnerModuleDao().readById(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPartnerModuleDao().deleteRelation(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to get all modules

        addLink(PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.GET_ALL_MODULES, getAcceptRequestHeader(), this.primaryId);
    }

}
