package de.fhws.fiw.fds.suttondemo.server.api.states.partners;

import de.fhws.fiw.fds.suttondemo.Start;

public interface PartnerUri {

    String PATH_ELEMENT = "partners";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
    String MODULE_PATH = REL_PATH_ID + "/modules";

}
