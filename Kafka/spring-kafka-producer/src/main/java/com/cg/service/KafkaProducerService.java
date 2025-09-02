package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cg.dto.Order;
import com.cg.dto.Product;

@Service
public class KafkaProducerService {

    @Autowired
    
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String PRODUCT_TOPIC = "product-topic";
    private static final String ORDER_TOPIC = "order-topic";

    public void sendProduct(Product product) {
        kafkaTemplate.send(PRODUCT_TOPIC, product);
        System.out.println(" Sent Product: " + product);
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send(ORDER_TOPIC, order);
        System.out.println("📤 Sent Order: " + order);
    }
}