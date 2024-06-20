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
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByModuleName;
import de.fhws.fiw.fds.suttondemo.server.api.queries.QueryByUniversityName;
import de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules.DeleteSingleModuleOfPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules.GetAllModulesOfPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules.GetSingleModuleOfPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules.PostNewModuleOfPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partnerModules.PutSingleModuleOfPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.DeleteSinglePartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.GetAllPartners;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.GetSinglePartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.PostNewPartner;
import de.fhws.fiw.fds.suttondemo.server.api.states.partners.PutSinglePartner;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
 
 @Path("partners")
 public class PartnerUniversityJerseyService extends AbstractJerseyService {
 
     public PartnerUniversityJerseyService() {
         super();
     }
 
     @GET
     @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response getAllPartners(
             @DefaultValue("") @QueryParam("universityname") final String uniName,
             @DefaultValue("0") @QueryParam("offset") int offset,
             @DefaultValue("20") @QueryParam("size") int size,
             @DefaultValue("") @QueryParam("order") String order)
             {
         try {
             return new GetAllPartners(
                     this.serviceContext,
                     new QueryByUniversityName<>(uniName, offset, size, order)
             ).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(e.getExceptionMessage(), e.getStatus().getCode());
         }
     }
 
     @GET
     @Path("{id: \\d+}")
     @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response getSinglePartner(@PathParam("id") final long id) {
         try {
             return new GetSinglePartner(this.serviceContext, id).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response
                     .status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build()
             );
         }
     }
 
     @POST
     @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response createSinglePartner(final PartnerUniversity partnerModel) {
         try {
             return new PostNewPartner(this.serviceContext, partnerModel).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @PUT
     @Path("{id: \\d+}")
     @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response updateSinglePartner(@PathParam("id") final long id, final PartnerUniversity partnerModel) {
         try {
             return new PutSinglePartner(this.serviceContext, id, partnerModel).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @DELETE
     @Path("{id: \\d+}")
     @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response deleteSinglePartner(@PathParam("id") final long id) {
         try {
             return new DeleteSinglePartner(this.serviceContext, id).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @GET
     @Path("{partnerId: \\d+}/modules")
     @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response getModulesOfPartner(@PathParam("partnerId") final long partnerId,
                                          @DefaultValue("0") @QueryParam("offset") int offset,
                                          @DefaultValue("20") @QueryParam("size") int size) {
         try {
             return new GetAllModulesOfPartner(this.serviceContext, partnerId, new QueryByModuleName<>(partnerId, offset, size)).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @GET
     @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
     @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response getModuleByIdOfPartner(@PathParam("partnerId") final long partnerId,
                                             @PathParam("moduleId") final long moduleId) {
         try {
             return new GetSingleModuleOfPartner( this.serviceContext, partnerId, moduleId ).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @POST
     @Path("{partnerId: \\d+}/modules")
     @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response createNewModuleOfPartner(@PathParam("partnerId") final long partnerId, final Module module) {
         try {
             return new PostNewModuleOfPartner( this.serviceContext, partnerId, module ).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @PUT
     @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
     @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Response updateNewModuleOfPartner(@PathParam("partnerId") final long partnerId,
                                               @PathParam("moduleId") final long moduleId, final Module module) {
         try {
             return new PutSingleModuleOfPartner( this.serviceContext, partnerId, moduleId, module ).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
     @DELETE
     @Path("{partnerId: \\d+}/modules/{moduleId: \\d+}")
     public Response deleteModuleOfPartner(@PathParam("partnerId") final long partnerId,
                                            @PathParam("moduleId") final long moduleId) {
         try {
             return new DeleteSingleModuleOfPartner( this.serviceContext, moduleId, partnerId ).execute();
         } catch (SuttonWebAppException e) {
             throw new WebApplicationException(Response.status(e.getStatus().getCode())
                     .entity(e.getExceptionMessage()).build());
         }
     }
 
 }
 