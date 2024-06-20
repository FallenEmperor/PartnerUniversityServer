package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import jakarta.ws.rs.core.Response;

public class GetAllPartners extends AbstractGetCollectionState<Response, PartnerUniversity> {

        public GetAllPartners(ServiceContext serviceContext, AbstractQuery<Response, PartnerUniversity> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to create new partner

        addLink(PartnerUri.REL_PATH, PartnerRelTypes.CREATE_PARTNER, getAcceptRequestHeader());

        // Link to get single partner

        addLink(PartnerUri.REL_PATH_ID, PartnerRelTypes.GET_SINGLE_PARTNER, getAcceptRequestHeader());

        // Links to different filtering methods

        addLink(PartnerUri.REL_PATH + "?universityname={PARTNERNAME}",PartnerRelTypes.FILTER_BY_NAME,getAcceptRequestHeader() );
        addLink(PartnerUri.REL_PATH + "?order={ORDER}", PartnerRelTypes.ORDERING, getAcceptRequestHeader());
        addLink(PartnerUri.REL_PATH + "?universityname={PARTNERNAME}&order={ORDER}", PartnerRelTypes.FILTERANDORDER, getAcceptRequestHeader());
    }

}
