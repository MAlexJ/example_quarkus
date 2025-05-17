package com.malex;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserService userService;

  @Inject
  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GET
  public List<User> findAll() {
    return userService.findAll();
  }

  @POST
  public List<User> add(User user) {
    userService.add(user);
    return userService.findAll();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") String id) {
    userService.invalidate(id);
    return Response.noContent().build();
  }

  @PUT
  public Response clearCache() {
    userService.clear();
    return Response.noContent().build();
  }
}
