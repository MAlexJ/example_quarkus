### Mongo Db

It uses the default Document class to interact with MongoDB
and manually converts between Document and your Fruit class.

#### Why this is not a POJO codec

You're doing this:

```
Document document = cursor.next();
new Fruit(document.getString("id"), ...);
```

and

```
new Document().append("name", fruit.name()).append(...);
```

This is manual mapping, and the collection is declared as:

```
MongoCollection<Document>
```

This means MongoDB just deals with generic BSON Document, and you parse it yourself.

#### Summary

```
Feature	                            Your Code Now	With POJO Codec
Uses Document	                        ✅ Yes	        ❌ No
Manual field extraction/insertion	    ✅ Yes	        ❌ No
Collection is typed with Fruit	        ❌ No	        ✅ Yes
Codec/Mapping needed	                ❌ No	        ✅ Yes
Clean, declarative Mongo usage	        ❌ No	        ✅ Yes
```