##Run the Application on Kafka
### Step1 : Setup Kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

Step 2: Start Consumer Service (spring-kafka-consumer) → listens on port 8082

Step 3: Start Producer Service (spring-kafka-producer) → runs on port 8081

Step 4: Test via Postman:
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
Step 5: Check logs from consumer
Consumed Product: Product{id=1, name='Laptop', price=75000.0}
Consumed Order: Order{orderId=101, productName='Laptop', quantity=2}





