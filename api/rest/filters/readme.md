### Request or response filters


link: https://quarkus.io/guides/rest#request-or-response-filters

#### Via annotations

You can declare functions that are invoked in the following phases of the request processing:

* Before the endpoint method is identified: pre-matching request filter
* After routing, but before the endpoint method is called: normal request filter
* After the endpoint method is called: response filter

These filters allow you to do various things such as examine the request URI, HTTP method, influence routing, 
look or change request headers, abort the request, or modify the response.

Request filters can be declared with the @ServerRequestFilter annotation:

```
import java.util.Optional;

class Filters {

    @ServerRequestFilter(preMatching = true)
    public void preMatchingFilter(ContainerRequestContext requestContext) {
        // make sure we don't lose cheese lovers
        if("yes".equals(requestContext.getHeaderString("Cheese"))) {
            requestContext.setRequestUri(URI.create("/cheese"));
        }
    }

    @ServerRequestFilter
    public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {
        // only allow GET methods for now
        if(!ctx.getMethod().equals(HttpMethod.GET)) {
            return Optional.of(RestResponse.status(Response.Status.METHOD_NOT_ALLOWED));
        }
        return Optional.empty();
    }
}
```

Similarly, response filters can be declared with the @ServerResponseFilter annotation:

```
class Filters {
    @ServerResponseFilter
    public void getFilter(ContainerResponseContext responseContext) {
        Object entity = responseContext.getEntity();
        if(entity instanceof String) {
            // make it shout
            responseContext.setEntity(((String)entity).toUpperCase());
        }
    }
}
```