package com.cg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.Product;
import com.cg.entity.Order;
import com.cg.exception.ResourceNotFoundException;
import com.cg.feign.ProductClient;
import com.cg.service.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oapi")
@EnableFeignClients
public class OrderController {

    @Autowired
    IOrderService orderService;
    
    @Autowired
    private ProductClient productClient;

    // Get all orders
    @GetMapping(path = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Order> orders() {
        return orderService.getOrdersFromDatabase();
    }

    // Demo of @PathVariable
    @GetMapping("/orders/db/{id}")
    Order findByOrderIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
    }

    // Demo of @RequestParam: http://localhost:9090/oapi/orders?id=1
    @GetMapping("/orders/{id}")
    Order findByOrderIdUsingRequestParam(@RequestParam int id) throws ResourceNotFoundException {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
    }

    // Create new order
    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
    }

    // Update order
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Integer orderId,
                                             @Valid @RequestBody Order newOrder) throws ResourceNotFoundException {
        return orderService.updateOrder(orderId, newOrder);
    }

    // Delete order
    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer orderId)
            throws ResourceNotFoundException {
        return orderService.deleteOrder(orderId);
    }
    
    //---------Feign -----------
    
    @GetMapping("/orders/products")
    public List<Product> getProductsFromProductService() {
        return productClient.getAllProducts();
    }
    
    
    @GetMapping("/orders/products/{id}")
    public Product getProductByIdFromProductService(@PathVariable int id) {
        return productClient.getProductById(id);
    }
}
