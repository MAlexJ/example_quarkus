package com.malex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class FruitService {

  /*
   * To declaring a thread-safe container (AtomicReference) that will hold a reference to a single FruitEntity object.
   */
  private final AtomicReference<FruitEntity> ref = new AtomicReference<>();

  private final FruitMapper mapper;

  @Inject
  public FruitService(FruitMapper mapper) {
    this.mapper = mapper;
  }

  public FruitResponse finOne() {
    var entity = ref.get();
    return mapper.toResponse(entity);
  }

  public void add(FruitRequest request) {
    var entity = mapper.toEntity(request);
    ref.getAndSet(entity);
  }
}
