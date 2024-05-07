package de.fhws.fiw.fds.sutton.server.api.states;


import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class DemoStateThrowsWebException extends AbstractState<Response, Void> {
    public DemoStateThrowsWebException() {
        super(new AbstractStateBuilder<>() {
            @Override
            public AbstractState<Response, Void> build() {
                return null;
            }
        });
    }

    @Override
    protected Response buildInternal() {
        throw new WebApplicationException();
    }
}
