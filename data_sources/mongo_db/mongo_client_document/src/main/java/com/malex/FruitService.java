package com.malex;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

@ApplicationScoped
public class FruitService {

  private final MongoClient client;

  @Inject
  public FruitService(MongoClient mongoClient) {
    this.client = mongoClient;
  }

  public List<Fruit> list() {
    List<Fruit> list = new ArrayList<>();

    try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
      while (cursor.hasNext()) {
        Document document = cursor.next();
        var fruit =
            new Fruit(
                document.getString("id"),
                document.getString("name"),
                document.getString("description"));
        list.add(fruit);
      }
    }
    return list;
  }

  public void add(Fruit fruit) {
    Document document =
        new Document().append("name", fruit.name()).append("description", fruit.description());
    getCollection().insertOne(document);
  }

  private MongoCollection<Document> getCollection() {
    return client.getDatabase("fruits").getCollection("fruit", Document.class);
  }
}
