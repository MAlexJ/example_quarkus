package com.malex;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FruitService {

  @ConfigProperty(name = "quarkus.mongodb.database")
  String dbName;

  private final MongoClient client;

  @Inject
  public FruitService(MongoClient mongoClient) {
    this.client = mongoClient;
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
    return client
        /*
         * default database:
         * @ConfigProperty(name = "quarkus.mongodb.database") String dbName
         */
        .getDatabase(dbName)
        .getCollection("fruit", Fruit.class); // POJO codec in action
  }
}
