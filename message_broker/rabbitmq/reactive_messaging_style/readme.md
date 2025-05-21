### Reactive messaging style

#### quarkus-messaging-rabbitmq:

* Reactive only: It uses SmallRye Reactive Messaging and Mutiny under the hood.
* No blocking I/O.
* Ideal for:
    * streaming data,
    * event-driven microservices,
    * low-latency applications,
    * handling backpressure, retries, etc.
* Uses channels and emitters (@Channel, Emitter<T>) to interact with RabbitMQ topics or queues.
* Integrates naturally with Multi<T> (SSE, REST streams, etc.).

#### To use reactive messaging with RabbitMQ in Quarkus, you’ll need a minimal set of annotations and configuration.

* Required Dependency

```
'implementation 'io.quarkus:quarkus-messaging-rabbitmq'
```

##### Core Annotations

1. **@Incoming("channel-name")** - Used to consume messages from a channel (e.g., RabbitMQ queue).

```
@Incoming("quotes")
public void receiveQuote(Quote quote) {
    // handle message
}
```

Minimal Reactive Consumer, example:

```
@ApplicationScoped
public class QuoteConsumer {

    @Incoming("quotes")
    public void consume(Quote quote) {
        System.out.println("Received quote: " + quote);
    }
}
```

2. **@Outgoing("channel-name")** - Used to produce messages to a channel.

```
@Outgoing("quote-requests")
public Multi<String> generateRequests() {
    return Multi.createFrom().items("req-1", "req-2");
}
```

3. **@Channel("channel-name")** - Used for programmatic interaction (e.g., using Emitter).

```
@Inject
@Channel("quote-requests")
Emitter<String> emitter;
```

Minimal Reactive Producer:

```
@ApplicationScoped
public class QuoteProducer {

    @Inject
    @Channel("quote-requests")
    Emitter<String> emitter;

    public void requestQuote() {
        emitter.send(UUID.randomUUID().toString());
    }
}
```

#### Reactive Types

* Multi<T> — a stream of elements (used for reactive reads or SSE).
* Emitter<T> — a programmatic API to send messages.
* Uni<T> — single async result (can be used too, but less common in RabbitMQ context).

#### Minimal Application Configuration

```
# === OUTGOING CHANNEL CONFIGURATION ===
mp.messaging.outgoing.quote-requests.connector=smallrye-rabbitmq
mp.messaging.outgoing.quote-requests.exchange.name=quote-requests
mp.messaging.outgoing.quote-requests.exchange.type=fanout
mp.messaging.outgoing.quote-requests.host=*********
mp.messaging.outgoing.quote-requests.port=5671
mp.messaging.outgoing.quote-requests.username=*********
mp.messaging.outgoing.quote-requests.password=*********

# === INCOMING CHANNEL CONFIGURATION ===
mp.messaging.incoming.quotes.connector=smallrye-rabbitmq
mp.messaging.incoming.quotes.queue.name=quotes
mp.messaging.incoming.quotes.host=*********
mp.messaging.incoming.quotes.port=5671
mp.messaging.incoming.quotes.username=*********
mp.messaging.incoming.quotes.password=*********
```

**note**: Why exchange.name and exchange.type are missing in the incoming configuration
In RabbitMQ, when you're consuming messages (i.e., incoming in SmallRye/Quarkus),
you bind to a queue — not directly to an exchange.

That’s why:

* The exchange configuration is optional or not required for mp.messaging.incoming.*.

* The queue may already be bound to the exchange on the broker side (by your publisher or by a RabbitMQ policy),
  so Quarkus just needs to know what queue to listen to.

#### Incoming Configuration — Minimum Required

```
mp.messaging.incoming.quotes.connector=smallrye-rabbitmq
mp.messaging.incoming.quotes.queue.name=quotes
```

#### Outgoing Configuration — Needs Exchange

In contrast, for producing messages (i.e., outgoing), RabbitMQ requires:

* The exchange name,
* The exchange type (fanout, direct, topic, etc.),
* Optionally a routing key.

So this is mandatory in outgoing:

```
mp.messaging.outgoing.quote-requests.connector=smallrye-rabbitmq
mp.messaging.outgoing.quote-requests.exchange.name=quote-requests
mp.messaging.outgoing.quote-requests.exchange.type=fanout
```

Summary:

```
------------------------------------------------------------------------------------------
Config Role	      Required Fields	                    Optional (Only if declaring)
------------------------------------------------------------------------------------------
Incoming	        queue.name	                    exchange.name, exchange.type (if bind)
Outgoing	    exchange.name, exchange.type	    routing-key, message TTL, etc.
```

Feature:

```
------------------------------------------------------------------------------------------------------------------------
Feature	                      quarkus-messaging-rabbitmq	                            quarkus-rabbitmq-client
------------------------------------------------------------------------------------------------------------------------
Style	                    Reactive (non-blocking)	                              Imperative (blocking by default)
Use case	                Streaming, pub/sub, reactive microservices	          Fine-grained control, request/response
API	                        SmallRye Reactive Messaging (based on Mutiny)	      Vert.x RabbitMQ Client
Message handling	        Annotated (@Incoming, @Outgoing, @Channel)	          Manual (send(), basicConsume(), etc)
Suitable for native builds	✅ Yes	                                              ✅ Yes
Easy RabbitMQ integration	✅ High-level abstraction	                          ✅ Low-level flexibility
```

When to use quarkus-messaging-rabbitmq?

* When you want to write declarative, reactive messaging pipelines.
* When you use reactive REST endpoints (e.g., SSE).
* When you prefer less boilerplate and better integration with Quarkus's reactive stack.
* If you're building event-driven or streaming systems.

