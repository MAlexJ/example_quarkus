package com.malex;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FruitService {

  private final MongoClient mongoClient;

  @Inject
  public FruitService(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public List<Fruit> list() {
    List<Fruit> list = new ArrayList<>();
    try (MongoCursor<Fruit> cursor = getCollection().find().iterator()) {
      while (cursor.hasNext()) {
        list.add(cursor.next());
      }
    }
    return list;
  }

  public void add(Fruit fruit) {
    getCollection().insertOne(fruit);
  }

  private MongoCollection<Fruit> getCollection() {
    return mongoClient.getDatabase("fruit").getCollection("fruit", Fruit.class);
  }
}
