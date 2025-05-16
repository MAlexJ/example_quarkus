package com.malex;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserService {

  private static final Logger log = Logger.getLogger(UserService.class);

  public static final String USERS_CACHE = "users-cache";

  private final List<User> users = new CopyOnWriteArrayList<>();

  @CacheResult(cacheName = USERS_CACHE)
  public List<User> findAll() {
    log.info("find all users");
    return users;
  }

  @CacheInvalidate(cacheName = USERS_CACHE)
  public void add(User user) {
    log.info("dd user");
    users.add(user);
  }

  @CacheInvalidate(cacheName = USERS_CACHE)
  public void invalidate(String id) {
    log.info("remove user and invalidate cache");
    users.removeIf(user -> user.id().equals(id));
  }

  @CacheInvalidateAll(cacheName = USERS_CACHE)
  public void clear() {
    log.info("invalidate user cache");
    users.clear();
  }
}
