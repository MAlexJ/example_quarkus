###   

#### Architecture

In this guide, we are going to develop two applications communicating with a RabbitMQ broker.

```
          |<--(4) SSE --------|***************|<--(3) Quotes (AMQP Queue)---------|***************|
 ClientUI |                  | Quote Producer  |                                 | Quote Processor |
          |-->(1) HTTP POST---|***************|-->(2) Quote-Request (AMQP Queue)->|***************|
```        

The **first** application sends a **quote request** to the RabbitMQ quote requests exchange
and consumes messages from the **quote queue**.

The **second** application receives the **quote request** and sends a **quote** back.

The first application, the producer, will let the user request some quotes over an HTTP endpoint.

For each quote request, a random identifier is generated and returned to the user,
to put the quote request on pending.

At the same time the generated request id is sent to the quote-requests exchange.

```
Quotes:
    Quote # e7d36ece-ea59-41ae-b606-50039ecdee37 | $ 16
    Quote # 7fb5019d-294c-40a6-a2bb-29c28560effb | $ 74
    Quote # 22def4c4-8e74-48dd-a4c4-30739419c5f2 | $ 58
    Quote # fece3e31-7e9e-4323-bdeb-b8a1e24198e6 | $ 17
    Quote # 4e182cb5-a617-4f78-8037-cfc605919ad1 | $ 69
    ...
    Quote # 7327b261-b6be-42b3-815b-2438e683a549 | $ 18
```

The second application, the processor, in turn, will read from the quote-requests queue put a random price to the quote,
and send it to an exchange named quotes.

Lastly, the producer will read the quotes and send them to the browser using server-sent events. The user will therefore
see the quote price updated from pending to the received price in real-time.

link: https://quarkus.io/guides/rabbitmq