package com.malex.filters;

import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Table 8. Filter parameters
 *
 * ContainerRequestContext - A context object to access the current request
 * Only for response: ContainerResponseContext - A context object to access the current response
 * Only for response: Throwable - Any thrown and handled exception, or null (only for response filters).
 *
 */
public class RequestFilter {

  private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);

  /*
   * Before the endpoint method is identified: pre-matching request filter
   */
  @ServerRequestFilter(preMatching = true)
  public void preMatchingFilter(ContainerRequestContext ctx) {
    log.info("Pre-matching filter");

    // Option 1: Use setRequestUri(baseUri, newUri)
    if ("redirect".equals(ctx.getHeaderString("X-AUTH"))) {
      URI baseUri = ctx.getUriInfo().getBaseUri(); // typically "/"
      URI newUri = URI.create(baseUri.toString() + "rest/api/redirect");
      ctx.setRequestUri(baseUri, newUri);
    }

    // Option 2: Respond manually with a redirect (302)
    //    if ("redirect".equals(ctx.getHeaderString("X-AUTH"))) {
    //      return Optional.of(RestResponse.status(Response.Status.TEMPORARY_REDIRECT)
    //              .header("Location", "/rest/api/redirect"));
    //    }
  }

  /*
   * After routing, but before the endpoint method is called: normal request filter
   */
  @ServerRequestFilter
  public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {
    log.info("Normal filter");
    // only allow GET methods for now
    if (!ctx.getMethod().equals(HttpMethod.GET)) {
      return Optional.of(RestResponse.status(Response.Status.METHOD_NOT_ALLOWED));
    }
    return Optional.empty();
  }
}
