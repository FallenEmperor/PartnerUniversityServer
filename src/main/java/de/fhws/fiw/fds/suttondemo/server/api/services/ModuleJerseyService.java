
package de.fhws.fiw.fds.suttondemo.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.states.modules.DeleteSingleModule;
import de.fhws.fiw.fds.suttondemo.server.api.states.modules.GetAllModules;
import de.fhws.fiw.fds.suttondemo.server.api.states.modules.GetSingleModule;
import de.fhws.fiw.fds.suttondemo.server.api.states.modules.PostNewModule;
import de.fhws.fiw.fds.suttondemo.server.api.states.modules.PutSingleModule;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("modules")
public class ModuleJerseyService extends AbstractJerseyService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllModules() {
        try {
            return new GetAllModules( this.serviceContext, new GetAllModules.AllModules<>( ) ).execute( );
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSingleModule(@PathParam("id") final long id) {
        try {
            return new GetSingleModule( this.serviceContext, id ).execute( );
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSingleLocation(final Module moduleModel) {
        try {
            return new PostNewModule( this.serviceContext, moduleModel ).execute( );
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSingleLocation(@PathParam("id") final long id, final Module moduleModel) {
        try {
            return new PutSingleModule( this.serviceContext, id, moduleModel ).execute( );
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSingleModule(@PathParam("id") final long id) {
        try {
            return new DeleteSingleModule( this.serviceContext, id ).execute( );
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

}
