package de.fhws.fiw.fds.suttondemo.client.web;

import java.io.IOException;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.ModuleModel;

public class ModuleWebClient {

    private GenericWebClient<ModuleModel> client;

    public ModuleWebClient() {
        this.client = new GenericWebClient<>();
    }

    public ModuleWebResponse getSingleModule(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, ModuleModel.class));
    }

    public ModuleWebResponse getCollectionOfModules(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, ModuleModel.class));
    }

    public ModuleWebResponse postNewModule(String url, ModuleModel module)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, module));
    }

    public ModuleWebResponse putModule(String url, ModuleModel module) throws IOException {
        return createResponse(this.client.sendPutRequest(url, module));
    }

    public ModuleWebResponse deleteModule(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public ModuleWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    private ModuleWebResponse createResponse(WebApiResponse<ModuleModel> response) {
        return new ModuleWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }

}
