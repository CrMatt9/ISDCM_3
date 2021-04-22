/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.isdcm_rest.resources;

import isdcm.models.Video;
import java.sql.SQLException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.json.JsonObject;
import javax.json.Json;
import javax.ws.rs.core.Response;

@Path("video")
@RequestScoped
public class restVideo {

    @Context
    private UriInfo context;

    public restVideo() {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(JsonObject content){
        try {
            boolean oRes = Video.updateReproductions(content.getInt("id_video"), content.getInt("id_user"));
            if (oRes) return Response.ok(Video.getVideoById(content.getInt("id_video")), MediaType.APPLICATION_JSON).build();
            else return Response.serverError().entity("{ \"error\": \"Video not found\" }").build();
        } catch (SQLException handlerException) {
            return Response.serverError().entity("{ \"error\": \"Video not found\" }").build();
        }
    }
    
}
