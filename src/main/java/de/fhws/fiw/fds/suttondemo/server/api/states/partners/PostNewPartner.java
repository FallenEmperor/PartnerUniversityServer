package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewPartner extends AbstractPostState<Response, PartnerUniversity> {

    public PostNewPartner(ServiceContext serviceContext, PartnerUniversity modelToStore) {
        super(serviceContext, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getPartnerDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

        // Link to get all partners

        addLink( PartnerUri.REL_PATH, PartnerRelTypes.GET_ALL_PARTNERS, getAcceptRequestHeader( ));

        // Link to create another partner

        addLink(PartnerUri.REL_PATH, PartnerRelTypes.CREATE_PARTNER, getAcceptRequestHeader());

    }

}
