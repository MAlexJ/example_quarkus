package com.malex.filters;

import jakarta.ws.rs.container.ContainerResponseContext;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

/*
 * Table 8. Filter parameters
 *
 * Only for request: ContainerRequestContext - A context object to access the current request
 * ContainerResponseContext - A context object to access the current response
 * Throwable - Any thrown and handled exception, or null (only for response filters).
 *
 */
public class ResponseFilter {

  @ServerResponseFilter
  public void getFilter(ContainerResponseContext responseContext, Throwable throwable) {
    Object entity = responseContext.getEntity();
    if (entity instanceof String) {
      // make it shout
      responseContext.setEntity(((String) entity).toUpperCase());
    }
  }
}
