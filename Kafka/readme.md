## Run the Application on Kafka

### Step 0: Download Kafka
### Kafka Setup C:\Software\SFT_BIN\kafka
1. Extract C:\Software\SFT_BIN>tar -xvzf kafka_2.12-3.9.1.tgz
2. Create a Kafka folder - copy the extracted content to Kafka folder.
3. Powershell - Start the Kafka Server
   ```
5. PS C:\Software\SFT_BIN\kafka> .\bin\windows\kafka-storage.bat random-uuid
      It will geernate a cluster id: 4bQqUo6xQ9-gbJy0xTZVxg
```
```
PS C:\Software\SFT_BIN\kafka> .\bin\windows\kafka-storage.bat format -t 4bQqUo6xQ9-gbJy0xTZVxg -c .\config\kraft\server.properties
```
      Formatting metadata directory /tmp/kraft-combined-logs with metadata.version 3.9-IV0.
      
      a. kafka-storage.bat format → formats the Kafka log directory for first-time use.
			b. -t <UUID> → the cluster ID you generated with random-uuid.
			c. -c .\config\kraft\server.properties → tells Kafka which configuration file to use.
```
7. Create Topic:
	C:\Software\SFT_BIN\kafka>.\bin\windows\kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092
					Created topic test-topic.
```
```
8. List topics :
	C:\Software\SFT_BIN\kafka>.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
				test-topic
```

```       
9. Create Producer : C:\Software\SFT_BIN\kafka>.\bin\windows\kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092
>hello
```
```
8. C:\Software\SFT_BIN\kafka>.\bin\windows\kafka-console-consumer.bat --topic test-topic --from-beginning --bootstrap-server localhost:9092
```
//-----------------------------------------

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




