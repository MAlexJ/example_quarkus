package com.malex;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/weather")
public class WeatherResource {

  @Inject WeatherService service;

  @GET
  @Produces("application/json")
  @Consumes("application/json")
  @RunOnVirtualThread
  public Response getTheBestPlaceToBe() {
    return Response.ok(service.getWeather()).build();
  }
}
