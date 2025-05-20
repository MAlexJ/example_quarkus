### Message broker selection cheat sheet: Kafka vs RabbitMQ vs Amazon SQS

link: https://habr.com/ru/articles/716182/

This is a series of articles dedicated to the optimal choice between different systems on a real project or an
architectural interview.
At work or at a System Design interview, you often have to choose the best message broker. I plunged into this issue and
will tell you what and why. What is better in each case, what are the advantages and disadvantages of these systems, and
which one to choose, I will show with several examples.

Main differences between **Kafka**, **RabbitMQ**, and **Amazon SQS**:

#### Architecture:

* Kafka is a distributed, publish-subscribe messaging system that uses a messaging queue as a durable store.
* RabbitMQ is a message broker that implements the Advanced Message Queuing Protocol (AMQP) and supports a wide range of
  messaging patterns.
* SQS is a managed message queue service that provides a simple and scalable way to transmit messages between
  applications.

#### Message Processing:

* Kafka is designed for high-throughput, real-time data streaming and batch processing. It supports parallel processing
  of messages using partitions.
* RabbitMQ supports a wide range of messaging patterns, including publish-subscribe, point-to-point, request-reply, and
  fan-out. It is also capable of parallel processing of messages using queues.
* SQS is designed for simple, asynchronous messaging between applications, with a focus on ease of use and scalability.
  It provides a reliable and highly available message queue service, but it does not support real-time data streaming or
  parallel processing.

#### Durability:

* Kafka provides a high level of durability for messages by storing them on disk and replicating them across multiple
  nodes in a cluster.
* RabbitMQ provides durability for messages by storing them on disk and keeping backups of messages on other nodes in a
  cluster.
* SQS provides a high level of durability for messages by automatically storing them redundantly across multiple
  Availability Zones in the same region.

#### Scalability:

* Kafka is highly scalable and can handle high volumes of messages. It can be horizontally scaled by adding more nodes
  to the cluster.
* RabbitMQ is also scalable, and it can be horizontally scaled by adding more nodes to the cluster.
* SQS is a fully managed service that is highly scalable, and it can automatically scale to handle the number of
  messages being sent and received.

#### Throughput:

* Kafka has been benchmarked to handle millions of events per second.
* RabbitMQ has a more modest throughput, typically handling tens of thousands of events per second.
* The exact throughput of SQS will depend on the number of messages being sent and received and the size of the
  messages, but it is generally able to handle thousands of requests per second.

#### Latency:

* Kafka is optimized for low latency, with message delivery times typically in the range of a few milliseconds.
* RabbitMQ has higher latency compared to Kafka, with message delivery times typically in the range of a few tens of
  milliseconds.
* The latency of SQS will depend on the number of messages being sent and received and the size of the messages, but it
  is generally able to deliver messages within a few seconds.

#### Cost:

* Kafka can be run on-premises or in the cloud, and the cost will depend on the hardware and infrastructure required to
  run the system.
* RabbitMQ can also be run on-premises or in the cloud, and the cost will depend on the hardware and infrastructure
  required to run the system.
* SQS is a fully managed service provided by Amazon Web Services (AWS), and the cost will depend on the number of
  requests made and the amount of data transferred.

#### Complexity:

* Kafka can be complex to set up and manage, especially at scale.
* RabbitMQ is less complex than Kafka, but still requires a certain level of technical expertise to set up and manage.
* SQS is a fully managed service, so it requires no setup or management, making it the simplest of the three systems to
  use.

In conclusion, the differences in numbers between Kafka, RabbitMQ, and SQS will depend on the specific requirements
of your application and use case:

* If you require high-throughput, low-latency messaging, then Kafka may be the best choice.
* If you need a more modest, versatile system that supports different messaging patterns,
  then RabbitMQ may be a good choice.
* If you require a simple, scalable, and fully managed message queue service in the cloud,
  then SQS may be the best choice.