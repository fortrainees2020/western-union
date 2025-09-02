## Run the Application on Kafka

### Step 0: Download Kafka
1. Download: https://kafka.apache.org/downloads
2. tar -xvzf kafka_2.13-2.7.1.tgz
   cd kafka_2.13-2.7.1
3. If you download kafka_2.13-2.7.1.tgz, you can right-click and use 7-Zip (or WinRAR) to extract it directly, no need to convert.

### Step1 : Setup Kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

### Step 2: Start Consumer Service (spring-kafka-consumer) → listens on port 8082

### Step 3: Start Producer Service (spring-kafka-producer) → runs on port 8081

### Step 4: Test via Postman:
POST http://localhost:8081/kafka/product
Content-Type: application/json

{
  "id": 1,
  "name": "Laptop",
  "price": 75000
}

POST http://localhost:8081/kafka/order
Content-Type: application/json

{
  "orderId": 101,
  "productName": "Laptop",
  "quantity": 2
}
### Step 5: Check logs from consumer
Consumed Product: Product{id=1, name='Laptop', price=75000.0}
Consumed Order: Order{orderId=101, productName='Laptop', quantity=2}


### Why Producer & Consumer are Microservices
1. Producer Service (spring-kafka-producer)
    - Independent Spring Boot application
    - Has its own REST API (/kafka/product, /kafka/order)
    - Publishes Product and Order messages to Kafka
2.  Consumer Service (spring-kafka-consumer)
    - Separate Spring Boot application
    - Subscribes to Kafka topics (product-topic, order-topic)
    - Processes Product and Order events independently

👉 Each one has its own codebase, dependencies, configuration, and lifecycle.
👉 They communicate only via Kafka (event-driven communication).
👉 They can scale, deploy, or fail independently.


### Example Microservice Flow
1. OrderService (producer) publishes a new order → order-topic.
2. InventoryService (consumer) listens to order-topic → reduces stock.
3. BillingService (consumer) listens to order-topic → generates invoice.
4. NotificationService (consumer) listens to both order-topic & product-topic → sends email/SMS.




