package de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules;

import de.fhws.fiw.fds.suttondemo.Start;

public interface PartnerModuleUri {

    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "partners/{id}/modules";

    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_ID = REL_PATH + "/{id}";

}
