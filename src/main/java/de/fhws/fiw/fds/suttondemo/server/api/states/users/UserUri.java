package de.fhws.fiw.fds.suttondemo.server.api.states.users;

import de.fhws.fiw.fds.suttondemo.Start;

public interface UserUri {

    String PATH_ELEMENT = "users";

    String REL_PATH = Start.CONTEXT_PATH + "api" + PATH_ELEMENT;

    String REL_PATH_ID = REL_PATH + "/{id}";
}
