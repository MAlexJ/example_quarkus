package com.malex;

import io.quarkus.cache.CacheKey;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/messages")
public class ExpensiveResource {

  private static final Log log = LogFactory.getLog(ExpensiveResource.class);

  private final ExpensiveService expensiveService;

  @Inject
  public ExpensiveResource(ExpensiveService expensiveService) {
    this.expensiveService = expensiveService;
  }

  @GET
  @Path("/{message}")
  public Response getExpensiveResponse(@PathParam("message") @CacheKey String message) {
    log.info("Http expensive response");
    return Response.ok(expensiveService.setAndGetExpensiveResponse(message)).build();
  }
}
