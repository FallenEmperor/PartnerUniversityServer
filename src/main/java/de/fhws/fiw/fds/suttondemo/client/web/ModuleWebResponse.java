package de.fhws.fiw.fds.suttondemo.client.web;

import java.util.Collection;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemo.client.models.ModuleModel;
import okhttp3.Headers;

public class ModuleWebResponse extends WebApiResponse<ModuleModel> {

    public ModuleWebResponse(final Collection<ModuleModel> responseData, final Headers headers, final int lastStatusCode) {
        super(responseData, headers, lastStatusCode);
    }

}

