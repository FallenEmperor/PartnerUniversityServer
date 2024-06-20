package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.PartnerRelTypes;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.PartnerUri;
import jakarta.ws.rs.core.Response;

public class GetAllModulesOfPartner extends AbstractGetCollectionRelationState<Response, Module> {

    public GetAllModulesOfPartner(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to create new module

        addLink(PartnerModuleUri.REL_PATH,
                PartnerModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);


        // Link to self

        addLink(PartnerModuleUri.REL_PATH_SHOW_ALL,
                PartnerModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);

        // Link to get single module

        addLink(PartnerModuleUri.REL_PATH_ID, PartnerModuleRelTypes.GET_SINGLE_MODULE, getAcceptRequestHeader(), this.primaryId);

        // Link to get back to single partner university
        
        addLink(PartnerUri.REL_PATH_ID, PartnerRelTypes.GET_SINGLE_PARTNER, getAcceptRequestHeader(), this.primaryId);
    }

}
