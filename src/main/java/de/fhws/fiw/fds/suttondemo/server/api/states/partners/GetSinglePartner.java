package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSinglePartner extends AbstractGetState<Response, PartnerUniversity> {

        public GetSinglePartner(ServiceContext serviceContext, long requestedId) {
        super(serviceContext, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerDao().readById(this.requestedId);
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String eTagOfModel = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(eTagOfModel);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to modules of single partner

        addLink( PartnerUri.MODULE_PATH, PartnerRelTypes.GET_ALL_MODULES_OF_PARTNER, getAcceptRequestHeader(), this.requestedId );

        // Link to create new module of partner

        addLink( PartnerUri.MODULE_PATH, PartnerRelTypes.CREATE_MODULE, getAcceptRequestHeader(), this.requestedId );

        // Link to update single partner

        addLink( PartnerUri.REL_PATH_ID, PartnerRelTypes.UPDATE_SINGLE_PARTNER, getAcceptRequestHeader( ),
                this.requestedId );

        // Link to delete single partner

        addLink( PartnerUri.REL_PATH_ID, PartnerRelTypes.DELETE_SINGLE_PARTNER, getAcceptRequestHeader( ),
                this.requestedId );

        // Link to go back to get all partners
        
        addLink(PartnerUri.REL_PATH, PartnerRelTypes.GET_ALL_PARTNERS, getAcceptRequestHeader());
    }
}
