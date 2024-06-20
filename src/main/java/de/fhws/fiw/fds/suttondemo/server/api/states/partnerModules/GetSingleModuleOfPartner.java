package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSingleModuleOfPartner extends AbstractGetRelationState<Response, Module> {

    public GetSingleModuleOfPartner(ServiceContext serviceContext, long primaryId, long requestedId) {
        super(serviceContext, primaryId, requestedId);
        this.suttonResponse = new JerseyResponse<>();
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

    @Override protected SingleModelResult<Module> loadModel( )
    {
        SingleModelResult<Module> module = DaoFactory.getInstance( ).getPartnerModuleDao( ).readById(this.primaryId, this.requestedId );
        if(isPartnerLinkedToThisModule()) {
            module.getResult().setPrimaryId(this.primaryId);
        }
        return module;
    }

    @Override protected void defineTransitionLinks( )
    {
        // Link to get all modules of partner university

        addLink( PartnerModuleUri.REL_PATH_SHOW_ALL,
                PartnerModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader( ),
                this.primaryId );

            // Link to update module of partner

            addLink( PartnerModuleUri.REL_PATH_ID,
                    PartnerModuleRelTypes.UPDATE_SINGLE_MODULE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            // Link to delete module of partner

            addLink( PartnerModuleUri.REL_PATH_ID,
                    PartnerModuleRelTypes.DELETE_MODULE_OF_PARTNER,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
    }


    private boolean isPartnerLinkedToThisModule( )
    {
        return !DaoFactory.getInstance( )
                .getPartnerModuleDao( )
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }
}