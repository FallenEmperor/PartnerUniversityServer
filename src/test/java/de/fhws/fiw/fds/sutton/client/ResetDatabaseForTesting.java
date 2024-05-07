package de.fhws.fiw.fds.sutton.client;

import java.io.IOException;

import de.fhws.fiw.fds.sutton.client.model.EmptyResource;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;

public class ResetDatabaseForTesting {
    public void reset() {
        final GenericWebClient<EmptyResource> webClient = new GenericWebClient<EmptyResource>();

        try {
            webClient.sendGetSingleRequest(IBaseUrl.BASE_URL + "resetdatabase");
            webClient.sendGetSingleRequest(IBaseUrl.BASE_URL + "initializedatabase");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}