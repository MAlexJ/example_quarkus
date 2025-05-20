package com.malex;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class ExpensiveService {

  public static final String EXPENSIVE_CACHE = "expensive-cache";

  private static final Log log = LogFactory.getLog(ExpensiveService.class);

  @CacheResult(cacheName = EXPENSIVE_CACHE)
  public ExpensiveResponse setAndGetExpensiveResponse(@CacheKey String message) {
    log.info("Get expensive response");
    return new ExpensiveResponse(message, LocalDateTime.now());
  }

  @CacheInvalidateAll(cacheName = "expensive-cache")
  public void invalidateAll() {
    log.info("Cache service Invalidate all");
  }
}
