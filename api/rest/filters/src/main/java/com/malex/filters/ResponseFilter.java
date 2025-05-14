package com.malex.filters;

import jakarta.ws.rs.container.ContainerResponseContext;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Table 8. Filter parameters
 *
 * Only for request: ContainerRequestContext - A context object to access the current request
 * ContainerResponseContext - A context object to access the current response
 * Throwable - Any thrown and handled exception, or null (only for response filters).
 *
 */
public class ResponseFilter {

  private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

  @ServerResponseFilter
  public void getFilter(ContainerResponseContext responseContext, Throwable throwable) {
    log.info("3. Response filter");
    Object entity = responseContext.getEntity();
    if (entity instanceof String) {
      // make it shout
      responseContext.setEntity(((String) entity).toUpperCase());
    }
  }
}
