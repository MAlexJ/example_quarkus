package com.malex;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

/*
 * Note: When using PanacheMongoEntity, the id field is handled for you — but you can also customize it if needed.
 */
public class Fruit extends PanacheMongoEntity {
  public String name;
  public String description;
}
