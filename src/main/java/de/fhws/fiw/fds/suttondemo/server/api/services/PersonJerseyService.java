/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo.server.api.services;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.ServletRequestAdapter.JerseyServletRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.requestAdapter.JerseyRequest;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.uriInfoAdapter.JerseyUriInfoAdapter;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;
import de.fhws.fiw.fds.suttondemo.server.api.models.Person;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByFirstAndLastName;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByLocationName;
import de.fhws.fiw.fds.suttondemo.server.api.states.person_locations.*;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("persons")
public class PersonJerseyService extends AbstractJerseyService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllPersons(
            @DefaultValue("") @QueryParam("firstname") final String firstName,
            @DefaultValue("") @QueryParam("lastname") final String lastName,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size,
            @DefaultValue("0") @QueryParam("wait") int waitingTime) {
        try {
            return new GetAllPersons.Builder<Response>()
                    .setQuery(new QueryByFirstAndLastName<>(firstName, lastName, offset, size, waitingTime))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSinglePerson(@PathParam("id") final long id) {
        try {
            return new GetSinglePerson.Builder<Response>()
                    .setRequestedId(id)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response
                    .status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build()
            );
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSinglePerson(final Person personModel) {
        try {
            return new PostNewPerson.Builder<Response>()
                    .setModelToCreate(personModel)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
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
    public Response updateSinglePerson(@PathParam("id") final long id, final Person personModel) {
        try {
            return new PutSinglePerson.Builder<Response>()
                    .setRequestedId(id)
                    .setModelToUpdate(personModel)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteSinglePerson(@PathParam("id") final long id) {
        try {
            return new DeleteSinglePerson.Builder<Response>()
                    .setRequestedId(id)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{personId: \\d+}/locations")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLocationsOfPerson(@PathParam("personId") final long personId,
                                         @DefaultValue("") @QueryParam("cityname") final String cityName,
                                         @DefaultValue("0") @QueryParam("offset") int offset,
                                         @DefaultValue("20") @QueryParam("size") int size,
                                         @DefaultValue("0") @QueryParam("wait") int waitingTime) {
        try {
            return new GetAllLocationsOfPerson.Builder<Response>()
                    .setParentId(personId)
                    .setQuery(new QueryByLocationName<>(personId, cityName, offset, size, waitingTime))
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<Collection<Location>>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getLocationByIdOfPerson(@PathParam("personId") final long personId,
                                            @PathParam("locationId") final long locationId) {
        try {
            return new GetSingleLocationOfPerson.Builder<Response>()
                    .setParentId(personId)
                    .setRequestedId(locationId)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<Location>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{personId: \\d+}/locations")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewLocationOfPerson(@PathParam("personId") final long personId, final Location location) {
        try {
            return new PostNewLocationOfPerson.Builder<Response>()
                    .setParentId(personId)
                    .setModelToCreate(location)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateNewLocationOfPerson(@PathParam("personId") final long personId,
                                              @PathParam("locationId") final long locationId, final Location location) {
        try {
            return new PutSingleLocationOfPerson.Builder<Response>()
                    .setParentId(personId)
                    .setRequestedId(locationId)
                    .setModelToUpdate(location)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{personId: \\d+}/locations/{locationId: \\d+}")
    public Response deleteLocationOfPerson(@PathParam("personId") final long personId,
                                           @PathParam("locationId") final long locationId) {
        try {
            return new DeleteSingleLocationOfPerson.Builder<Response>()
                    .setParentId(personId)
                    .setRequestedId(locationId)
                    .setUriInfo(new JerseyUriInfoAdapter(this.uriInfo))
                    .setSuttonRequest(new JerseyRequest(this.request))
                    .setSuttonServletRequest(new JerseyServletRequest(this.httpServletRequest))
                    .setSuttonResponse(new JerseyResponse<>())
                    .build()
                    .execute();
        } catch (SuttonWebAppException e) {
            throw new WebApplicationException(Response.status(e.getStatus().getCode())
                    .entity(e.getExceptionMessage()).build());
        }
    }

}
