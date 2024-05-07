package de.fhws.fiw.fds.suttondemo.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.JerseyServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.JerseyRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.JerseyUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.states.users.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
public class UserJerseyService extends AbstractJerseyService {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        try {
            return new GetAllUsers.Builder<Response>()
                    .setQuery(new GetAllUsers.AllUsers<>())
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserById(@PathParam("id") final long id) {
        try {
            return new GetUserById.Builder<Response>()
                    .setRequestedId(id)
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonResponse(new JerseyResponse<>())
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postNewUser(final User user) {
        try {
            return new PostNewUser.Builder<Response>()
                    .setModelToCreate(user)
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonResponse(new JerseyResponse<>())
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteUserById(@PathParam("id") final long userId, final User updatedUser) {
        try {
            return new PutUser.Builder<Response>()
                    .setRequestedId(userId)
                    .setModelToUpdate(updatedUser)
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonResponse(new JerseyResponse<>())
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    public Response deleteUserById(@PathParam("id") final long userId) {
        try {
            return new DeleteUser.Builder<Response>()
                    .setRequestedId(userId)
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonResponse(new JerseyResponse<>())
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }
}
