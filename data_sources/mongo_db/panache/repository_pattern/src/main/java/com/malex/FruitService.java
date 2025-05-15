package com.malex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FruitService {

  private final FruitRepository repository;

  @Inject
  public FruitService(FruitRepository repository) {
    this.repository = repository;
  }

  public List<Fruit> list() {
    return repository.listAll();
  }

  public List<Fruit> add(Fruit fruit) {
    repository.persist(fruit);
    return repository.listAll();
  }
}
