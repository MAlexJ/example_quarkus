### Rabbit MQ

The choice between:

* io.quarkus:quarkus-messaging-rabbitmq
* io.quarkiverse.rabbitmqclient:quarkus-rabbitmq-client

it depends on what level of abstraction you want to use in your Quarkus app.

#### quarkus-messaging-rabbitmq (Recommended for most use cases)

This is part of Quarkus' reactive messaging API and provides declarative messaging
using annotations like @Channel, @Outgoing, and @Incoming.

Use this if:

* You prefer a declarative, event-driven approach.
* You want integration with Quarkus messaging ecosystem (e.g., Kafka, MQTT, etc.).
* You don’t need to manage RabbitMQ channels or connections manually.

#### quarkus-rabbitmq-client (Low-level client)

This provides direct access to RabbitMQ Java client APIs
(com.rabbitmq.client.Connection, Channel, etc.).

It's better if:

* You want full manual control of queues, exchanges, and channels.
* You’re doing something advanced (like RPC, TTL headers, dead-lettering).
* You're porting legacy code that already uses RabbitMQ's Java client.

#### Which Should You Use?

```
Feature	quarkus-messaging-rabbitmq	quarkus-rabbitmq-client
Abstraction Level	High (declarative)	Low (manual, imperative)
Reactive Messaging Support	✅ Yes	❌ No
Easy integration with CDI	✅ Yes	⚠️ Manual
Ideal for Event-Driven Apps	✅ Yes	❌ Less ideal
Fine-grained control	❌ Limited	✅ Full control
```

#### Recommendation

Use quarkus-messaging-rabbitmq unless you have a specific need to manually control RabbitMQ
(then use quarkus-rabbitmq-client).