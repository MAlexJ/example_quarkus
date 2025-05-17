### Cache

#### Quarkus Cache Annotations

##### 1. @CacheResult

Purpose: Caches the result of a method invocation based on its parameters.

```
@CacheResult(cacheName = "my-cache")
public String compute(@CacheKey String input) {
    return "Result for " + input;
}
```

Used On: Methods.

Returns: Cached result from a previous call with the same key.

Attributes:

- cacheName – (required): the name of the cache.

##### 2. @CacheInvalidate

Purpose: Removes a specific entry from the cache based on method parameters.

```
@CacheInvalidate(cacheName = "my-cache")
public void evict(@CacheKey String key) {
    // Evicts only this key
}
```

Used On: Methods.

Attributes:

- cacheName – (required): the name of the cache.
- allEntries – (default: false): if true, evicts all entries (same as @CacheInvalidateAll).
- afterInvocation – (default: true): if false, cache is invalidated before method is invoked.

##### 3. @CacheInvalidateAll

Purpose: Clears all entries from the cache.

```
@CacheInvalidateAll(cacheName = "my-cache")
public void evictAll() {
    // Clears the cache
}
```

Used On: Methods.

Attributes:

- cacheName – (required): the name of the cache.
- afterInvocation – (default: true): if false, invalidation occurs before method call.

##### 4. @CacheKey

Purpose: Marks a method parameter as part of the cache key.

```
@CacheResult(cacheName = "cache")
public String lookup(@CacheKey String part1, @CacheKey int part2) {
    return "Composite key: " + part1 + part2;
}
```

Used On: Parameters.
Optional: If not used, all method parameters are considered for the key.

##### Additional Concepts (Not Annotations)

* Custom Key Generation

If you need advanced cache key handling, create a CacheKeyGenerator.

```
@ApplicationScoped
public class MyKeyGenerator implements CacheKeyGenerator {
    @Override
    public Object generate(CacheInvocationContext<?> context) {
        // Custom logic
    }
}
```

Then use it:

```
@CacheResult(cacheName = "custom-cache", keyGenerator = MyKeyGenerator.class)
public String compute(String input) { ... }
```

* Serialization

For Redis-based caches, cached values are serialized.
You must ensure:

- Return types are serializable (preferably simple DTOs).
- Avoid abstract types like Response (e.g., use ExpensiveResponse instead).
