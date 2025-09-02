package com.cg.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.Order;
import com.cg.dto.Product;
import com.cg.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducerService producer;
    
    @PostMapping("/product")
    public String publishProduct(@RequestBody Product product) {
        producer.sendProduct(product);
        return " Product published: " + product;
    }
    @PostMapping("/order")
    public String publishOrder(@RequestBody Order order) {
        producer.sendOrder(order);
        return " Order published: " + order;
    }
}
