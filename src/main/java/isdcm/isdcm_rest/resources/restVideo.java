package isdcm.isdcm_rest.resources;

import isdcm.models.Video;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

@Path("video")
@RequestScoped
public class restVideo {
    private static final String PARAM_ID_VIDEO = "id_video";
    private static final String PARAM_ID_USER = "id_user";
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(JsonObject content){
        try {
            int idVideo = content.getInt(PARAM_ID_VIDEO);
            int idUser = content.getInt(PARAM_ID_USER);
            
            boolean updated = Video.updateReproductions(idVideo, idUser);
            if (updated) return Response.ok(Video.getVideoById(idVideo), MediaType.APPLICATION_JSON).build();
            else return Response.serverError().entity(new Error("Video not found")).build();
        } catch (SQLException e) {
            return Response.serverError().entity(new Error("Video not found: " + e.getMessage())).build();
        }
    }
    
    public class Error {
        private String message;
        
        Error(String error) {
            this.message = error;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String error) {
            this.message = error;
        }
    }
}
