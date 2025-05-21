package com.malexj;

import io.netty.util.internal.ThreadLocalRandom;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

/**
 * A bean consuming data from the "quote-requests" RabbitMQ queue and giving out a random quote. The
 * result is pushed to the "quotes" RabbitMQ exchange.
 */
@ApplicationScoped
public class QuoteProcessor {

  private final Random random = new Random();

  @Incoming("requests")
  @Outgoing("quotes")
  @Blocking
  public Quote process(String quoteRequest) throws InterruptedException {
    // simulate some hard-working task
    int millis = ThreadLocalRandom.current().nextInt(1000, 2000);
    Thread.sleep(millis);

    return new Quote(quoteRequest, random.nextInt(100), LocalDateTime.now(ZoneId.of("UTC")));
  }
}
