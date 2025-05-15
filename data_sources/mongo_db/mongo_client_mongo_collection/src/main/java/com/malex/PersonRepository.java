package com.malex;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PersonRepository {

  @ConfigProperty(name = "quarkus.mongodb.database")
  String dbName;

  private final MongoCollection<PersonEntity> coll;

  @Inject
  public PersonRepository(MongoClient mongoClient) {

    /*
     * The type of codec used in your PersonRepository example is a POJO codec (PojoCodecProvider),
     * which is automatically used by MongoDB’s Java driver when you request a typed collection
     */
    this.coll =
        mongoClient
            /*
             * default database:
             * @ConfigProperty(name = "quarkus.mongodb.database") String dbName
             */
            .getDatabase(dbName)
            .getCollection("persons", PersonEntity.class);
  }

  public String add(PersonEntity person) {
    var bsonValueId = coll.insertOne(person).getInsertedId();
    return Optional.ofNullable(bsonValueId)
        .map(BsonValue::asObjectId)
        .map(BsonObjectId::getValue)
        .map(ObjectId::toHexString)
        .orElseThrow();
  }

  public List<PersonEntity> getPersons() {
    return coll.find().into(new ArrayList<>());
  }

  public long anniversaryPerson(String id) {
    Bson filter = eq("_id", new ObjectId(id));
    Bson update = inc("age", 1);
    return coll.updateOne(filter, update).getModifiedCount();
  }

  public long deletePerson(String id) {
    Bson filter = eq("_id", new ObjectId(id));
    return coll.deleteOne(filter).getDeletedCount();
  }
}
