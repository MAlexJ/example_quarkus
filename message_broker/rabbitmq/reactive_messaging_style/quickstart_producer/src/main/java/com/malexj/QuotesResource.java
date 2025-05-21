package com.malexj;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/quotes")
public class QuotesResource {

  /*
   * Inject a Reactive Messaging Multi to receive messages from the "quotes" RabbitMQ queue.
   *
   * Info:
   * Multi<T> — a stream of elements (used for reactive reads or SSE).
   */
  @Channel("quotes")
  Multi<Quote> quotes;

  /*
   * Inject a Reactive Messaging Emitter to send messages to the quote-requests channel.
   *
   * Info:
   * Emitter<T> — a programmatic API to send messages.
   */
  @Channel("quote-requests")
  Emitter<String> quoteRequestEmitter;

  /** Endpoint retrieving the "quotes" queue and sending the items to a server-sent event. */
  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public Multi<Quote> stream() {
    return quotes;
  }

  /*
   * Endpoint to generate a new quote request id and send it to the "quote-requests" channel
   * (which maps to the "quote-requests" RabbitMQ exchange) using the emitter.
   *
   * On a post-request, generate a random UUID and send it to the RabbitMQ queue using the
   * emitter.
   */
  @POST
  @Path("/request")
  @Produces(MediaType.TEXT_PLAIN)
  public String createRequest() {
    // 1. Generate a new UUID and send it to the "quote-requests" channel.
    var uuid = UUID.randomUUID();
    // 2. Send the UUID to the "quote-requests" channel.
    quoteRequestEmitter.send(uuid.toString());
    // 3. Return the UUID as a response.
    return uuid.toString();
  }
}
