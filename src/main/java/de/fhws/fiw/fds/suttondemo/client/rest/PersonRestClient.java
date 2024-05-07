package de.fhws.fiw.fds.suttondemo.client.rest;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.PersonClientModel;

import java.io.IOException;

public class PersonRestClient {

    private static final String BASE_URL = "http://localhost:8080/sd/api";

    private GenericWebClient<PersonClientModel> client;

    public PersonRestClient() {
        this.client = new GenericWebClient<>();
    }

    public PersonRestResponse start() throws IOException {
        return createResponse(this.client.sendGetSingleRequest(BASE_URL));
    }

    public PersonRestResponse readSinglePerson(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PersonClientModel.class));
    }

    public PersonRestResponse readCollectionOfPersons(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, PersonClientModel.class));
    }

    public PersonRestResponse createNewPerson(String url, PersonClientModel person)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, person));
    }

    public PersonRestResponse updatePerson(String url, PersonClientModel person) throws IOException {
        return createResponse(this.client.sendPutRequest(url, person));
    }

    public PersonRestResponse deletePerson(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PersonRestResponse resetDatabaseOnServer() throws IOException {
        return createResponse(this.client.sendGetSingleRequest(BASE_URL + "/resetdatabase"));
    }

    public PersonRestResponse initializeDatabaseOnServer() throws IOException {
        return createResponse(this.client.sendGetSingleRequest(BASE_URL + "/filldatabase"));
    }
    private PersonRestResponse createResponse(WebApiResponse<PersonClientModel> response) {
        return new PersonRestResponse(response);
    }
}
