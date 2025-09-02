package com.cg.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "product-topic", groupId = "group1")
    public void consumeProduct(com.cg.dto.Product product) {
        System.out.println(" Consumed Product: " + product);
    }

    @KafkaListener(topics = "order-topic", groupId = "group1")
    public void consumeOrder(com.cg.dto.Order order) {
        System.out.println(" Consumed Order: " + order);
    }
}
