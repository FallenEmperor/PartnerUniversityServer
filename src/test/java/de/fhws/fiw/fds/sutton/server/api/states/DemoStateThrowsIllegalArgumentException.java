package de.fhws.fiw.fds.sutton.server.api.states;


import jakarta.ws.rs.core.Response;

public class DemoStateThrowsIllegalArgumentException extends AbstractState<Response, Void> {
    public DemoStateThrowsIllegalArgumentException() {
        super(new AbstractStateBuilder<>() {
            @Override
            public AbstractState<Response, Void> build() {
                return null;
            }
        });
    }

    @Override
    protected Response buildInternal() {
        throw new IllegalArgumentException();
    }
}
