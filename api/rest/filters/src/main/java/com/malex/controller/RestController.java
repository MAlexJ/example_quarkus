package com.malex.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/rest/api")
public class RestController {

  private static final Logger log = LoggerFactory.getLogger(RestController.class);

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    log.info("Hello from Quarkus REST");
    return Response.ok("Hello from Quarkus REST").build();
  }

  @GET
  @Path("/redirect")
  @Produces(MediaType.APPLICATION_JSON)
  public Response redirect() {
    log.info("Redirect from Quarkus REST");
    return Response.ok(new RestResponse("Redirect")).build();
  }

  record RestResponse(String message) {}
}
