### Mongo Db

Note:

1. Using the default PojoCodecProvider (Quarkus default) for record or class
2. Using a Custom Codec (like your FruitCodec example) only for class

```
Case	                                    Use record?
Default Quarkus mapping	                    ✅ Yes
Custom Codec or CollectibleCodec           	❌ No
```

If you're using custom Codec, use a regular mutable class with getters/setters.

link: https://quarkus.io/guides/mongodb

