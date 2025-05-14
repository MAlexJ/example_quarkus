package com.malex;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.bson.Document;

@ApplicationScoped
public class ReactiveFruitService {

  private final ReactiveMongoClient mongoClient;

  @Inject
  public ReactiveFruitService(ReactiveMongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Uni<List<Fruit>> list() {
    return getCollection()
        .find()
        .map(
            doc ->
                new Fruit(doc.getString("id"), doc.getString("name"), doc.getString("description")))
        .collect()
        .asList();
  }

  public Uni<Void> add(Fruit fruit) {
    Document document =
        new Document().append("name", fruit.name()).append("description", fruit.description());
    return getCollection().insertOne(document).onItem().ignore().andContinueWithNull();
  }

  private ReactiveMongoCollection<Document> getCollection() {
    return mongoClient.getDatabase("fruit").getCollection("fruit");
  }
}
