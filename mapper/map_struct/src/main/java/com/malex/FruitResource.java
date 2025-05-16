package com.malex;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

  private final FruitService fruitService;

  @Inject
  public FruitResource(FruitService fruitService) {
    this.fruitService = fruitService;
  }

  @GET
  public Response finOne() {
    return Response.ok(fruitService.finOne()).build();
  }

  @POST
  public Response add(FruitRequest request) {
    fruitService.add(request);
    return Response.noContent().build();
  }
}
