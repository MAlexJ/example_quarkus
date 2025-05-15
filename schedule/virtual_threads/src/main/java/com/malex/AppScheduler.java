package com.malex;

import static io.vertx.codegen.CodeGenProcessor.log;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppScheduler {

  @RunOnVirtualThread
  @Scheduled(cron = "*/2 * * * * ?") // every 1 sec
  public void process() {
    log.info("Processing...");
  }
}
