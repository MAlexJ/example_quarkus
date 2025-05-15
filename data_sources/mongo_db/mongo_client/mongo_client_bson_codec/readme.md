### Simplifying MongoDB Client usage using BSON codec

link: https://quarkus.io/guides/mongodb#simplifying-mongodb-client-usage-using-bson-codec

By using a Bson Codec, the MongoDB Client will take care of the transformation of your domain object
to/from a MongoDB Document automatically.

Note:

1. Using the default PojoCodecProvider (Quarkus default) for record or class
2. Using a Custom Codec (like your FruitCodec example) only for class

```
Case	                                    Use record?
Default Quarkus mapping	                    ✅ Yes
Custom Codec or CollectibleCodec           	❌ No
```

If you're using custom Codec, use a regular mutable class with getters/setters.

#### Custom BSON codec in Quarkus:

1. Create the Fruit POJO.
2. Implement FruitCodec
3. Implement FruitCodecProvider.
4. Register the provider as a CDI @Singleton bean so Quarkus can pick it up automatically.
5. Inject MongoClient into a service.
6. Use getCollection("collectionName", Fruit.class) — your codec will be used automatically.
