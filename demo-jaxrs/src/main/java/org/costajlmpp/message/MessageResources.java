package org.costajlmpp.message;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResources {

    @Context
    HttpHeaders header;
    @Context
    HttpServletResponse response;
    @Context
    UriInfo uriInfo;

    @GET
    @Path("public")
    public Response getPublic() {

        return Response.ok(new Message("public"))
                .header("Access-Control-Allow-Origin", "*")
                .build();

    }

    @GET
    @Path("monitor")
    public Response getMonitor() {

        return Response.ok(new Message("Hello Monitor"))
                .header("Access-Control-Allow-Origin", "*")
                .build();

    }

    @GET
    @Path("admin")
    public Response getAdmin() {

        return Response.ok(new Message("Hello Admin"))
                .header("Access-Control-Allow-Origin", "*")
                .build();

    }
}
