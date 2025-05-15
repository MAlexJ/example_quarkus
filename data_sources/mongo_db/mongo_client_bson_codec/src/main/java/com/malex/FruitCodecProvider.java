package com.malex;

import jakarta.inject.Singleton;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/*
 * Custom BSON codec in Quarkus:
 * 1. Create the Fruit POJO.
 * 2. Implement FruitCodec
 * 3. Implement FruitCodecProvider.
 * 4. Register the provider as a CDI @Singleton bean so Quarkus can pick it up automatically.
 * 5. Inject MongoClient into a service.
 * 6. Use getCollection("collectionName", Fruit.class) — your codec will be used automatically.
 */
@Singleton
public class FruitCodecProvider implements CodecProvider {

  @Override
  @SuppressWarnings("unchecked")
  public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
    if (clazz.equals(Fruit.class)) {
      return (Codec<T>) new FruitCodec();
    }
    return null;
  }
}
