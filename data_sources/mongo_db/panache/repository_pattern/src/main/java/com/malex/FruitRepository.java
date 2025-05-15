package com.malex;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitRepository implements PanacheMongoRepository<Fruit> {
    // Optional custom methods like findByName, etc.
}
