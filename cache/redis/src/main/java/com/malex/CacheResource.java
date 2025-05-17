package com.malex;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/cache")
public class CacheResource {

  private static final Log log = LogFactory.getLog(CacheResource.class);

  private final ExpensiveService expensiveService;

  @Inject
  public CacheResource(ExpensiveService expensiveService) {
    this.expensiveService = expensiveService;
  }

  @GET
  @Path("/invalidate")
  public Response invalidateAll() {
    log.info("Http invalidate cache");
    expensiveService.invalidateAll();
    return Response.noContent().build();
  }
}
