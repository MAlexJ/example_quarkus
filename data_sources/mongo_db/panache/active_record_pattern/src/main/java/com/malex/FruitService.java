package com.malex;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FruitService {

  public List<Fruit> list() {
    return Fruit.listAll();
  }

  public void add(Fruit fruit) {
    fruit.persist(); // This persists the instance
  }
}
