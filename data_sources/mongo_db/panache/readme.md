### Quarkus Panache for MongoDB

Quarkus Panache for MongoDB is a convenient abstraction layer built on top of MongoDB client,
which makes working with MongoDB documents feel simple,
clean, and similar to JPA (like Hibernate) but for NoSQL.

##### You no longer need to:

* Manually create filters,
* Handle collections,
* Or manage cursors.

* Instead, you just define a Java class and use high-level methods like persist(), find(), delete(), etc.

Setup

1. Add Dependency

   In build.gradle: ```implementation 'io.quarkus:quarkus-mongodb-panache'```

Example

2. Entity Class

```
    import io.quarkus.mongodb.panache.PanacheMongoEntity;

    public class Person extends PanacheMongoEntity {
        public String name;
        public int age;
    }
```

PanacheMongoEntity includes the _id field automatically.

4. Repository (Optional)

5. There are two ways to use Panache:
    * Active Record style (simpler)
    * Repository style (more DDD-like)

#### Option A: Active Record Style

Everything is inside the entity class.

```
public static List<Person> findByName(String name) {
   return find("name", name).list();
}
```

Use it directly like:

```
List<Person> people = Person.findByName("Alex");
Person.persist(new Person("Bob", 30));
```

#### Option B: Repository Style

More flexible. Create a separate repository class:

```
@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {

    public List<Person> findByName(String name) {
        return find("name", name).list();
    }

}
```

Use in a service:

```
@Inject
PersonRepository repository;

public void createPerson() {
   Person person = new Person();
   person.name = "John";
   person.age = 25;
   repository.persist(person);
}
```

#### Benefits of Panache

```
Traditional MongoDB Java Driver	                Panache
--------------------------------------------------------------------------
collection.insertOne(...)	                    persist()
collection.find(Filters.eq(...))	            find("field", value).list()
collection.deleteOne(...)	                    delete(...)
Requires codec setup and filters	            Zero boilerplate
Works with Document and manual mapping	        Direct Java class mapping
```

#### Internally

Panache uses:

* MongoDB client provided by Quarkus
* Built-in POJO codec (no need to manually configure PojoCodecProvider)
* Reflection or constructor injection