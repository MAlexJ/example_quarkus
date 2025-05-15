package com.malex;

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
public class FruitResource {

  FruitService fruitService;

  @Inject
  public FruitResource(FruitService fruitService) {
    this.fruitService = fruitService;
  }

  @GET
  public List<Fruit> list() {
    return fruitService.list();
  }

  @POST
  public List<Fruit> add(Fruit fruit) {
    fruitService.add(fruit);
    return fruitService.list();
  }
}
