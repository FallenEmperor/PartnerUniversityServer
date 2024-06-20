package de.fhws.fiw.fds.suttondemo.client.web;

import java.io.IOException;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.PartnerUniversityModel;

public class PartnerUniversityWebClient {

    private GenericWebClient<PartnerUniversityModel> client;

    public PartnerUniversityWebClient() {
        this.client = new GenericWebClient<>();
    }

    public PartnerUniversityWebResponse getDispatcher( String url ) throws IOException
    {
        return createResponse( this.client.sendGetSingleRequest( url ) );
    }


    public PartnerUniversityWebResponse getSinglePartner(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PartnerUniversityModel.class));
    }

    public PartnerUniversityWebResponse getCollectionOfPartners(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, PartnerUniversityModel.class));
    }

    public PartnerUniversityWebResponse postNewPartner(String url, PartnerUniversityModel partner)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, partner));
    }

    public PartnerUniversityWebResponse putPartner(String url, PartnerUniversityModel partner) throws IOException {
        return createResponse(this.client.sendPutRequest(url, partner));
    }

    public PartnerUniversityWebResponse deletePartner(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PartnerUniversityWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    public PartnerUniversityWebResponse initializeDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/initializedatabase"));
    }

    private PartnerUniversityWebResponse createResponse(WebApiResponse<PartnerUniversityModel> response) {
        return new PartnerUniversityWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }

}
