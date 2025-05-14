package com.malex;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {

  private final ReactiveFruitService fruitService;

  @Inject
  public ReactiveFruitResource(ReactiveFruitService fruitService) {
    this.fruitService = fruitService;
  }

  @GET
  public Uni<List<Fruit>> list() {
    return fruitService.list();
  }

  @POST
  public Uni<List<Fruit>> add(Fruit fruit) {
    return fruitService.add(fruit).onItem().ignore().andSwitchTo(this::list);
  }
}
