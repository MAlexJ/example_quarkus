package com.malex;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public abstract class FruitMapper {

  public abstract FruitResponse toResponse(FruitEntity entity);

  @Mapping(target = "id", expression = "java(generateId())")
  public abstract FruitEntity toEntity(FruitRequest dto);

  protected String generateId() {
    return UUID.randomUUID().toString();
  }
}
