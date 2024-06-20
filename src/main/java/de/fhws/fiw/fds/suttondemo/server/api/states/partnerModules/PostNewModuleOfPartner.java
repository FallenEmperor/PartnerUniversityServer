package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewModuleOfPartner extends AbstractPostRelationState<Response, Module> {

    public PostNewModuleOfPartner(ServiceContext serviceContext, long primaryId, Module modelToStore) {
        super(serviceContext, primaryId, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
        this.modelToStore.setPrimaryId(primaryId);
    }


    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getPartnerModuleDao( ).create( this.modelToStore.getPrimaryId(), this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

        // Link to get all modules for partner

        addLink(PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.GET_ALL_MODULES, getAcceptRequestHeader(), this.primaryId);

        // Link to create another new module

        addLink(PartnerModuleUri.REL_PATH, PartnerModuleRelTypes.CREATE_MODULE, getAcceptRequestHeader());

    }
}
