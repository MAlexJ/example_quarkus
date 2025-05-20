### Cloud RabbitMQ configuration

Add rabbitMQ properties to `.env` file:

```
RABBITMQ_HOST=cow.rmq2.cloudamqp.com
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=......
RABBITMQ_PASSWORD=........
RABBITMQ_VIRTUAL_HOST=......
```

Note: for CloudAMQP RabbitMQ provider `RABBITMQ_VIRTUAL_HOST` is `RABBITMQ_USERNAME`

#### Provider

* https://www.cloudamqp.com (Message Queues in the Cloud)

##### CloudAMQP RabbitMQ provider

link: https://api.cloudamqp.com

plan: 'Little Lemur'

* Open Connections: 0 of 20
* Max Idle Queue Time: 28 days
* Queues: 2 of 150
* Messages: 7 of 1 000 000
* Queue Length: 1 of 10 000
