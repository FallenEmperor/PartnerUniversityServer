package de.fhws.fiw.fds.suttondemo.client.web;

import java.util.Collection;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.PartnerUniversityModel;
import okhttp3.Headers;

public class PartnerUniversityWebResponse extends WebApiResponse<PartnerUniversityModel> {

    public PartnerUniversityWebResponse(final Collection<PartnerUniversityModel> responseData, final Headers headers, final int lastStatusCode) {
        super(responseData, headers, lastStatusCode);
    }

}
