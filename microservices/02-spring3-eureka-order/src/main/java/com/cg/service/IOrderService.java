package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.entity.Order;
import com.cg.exception.ResourceNotFoundException;

public interface IOrderService {
    
    List<Order> getOrdersFromDatabase();
    
    Optional<Order> getOrderById(int id);
    
    Order createOrder(Order order);
    
    ResponseEntity<Order> updateOrder(Integer orderId, Order orderDetails) throws ResourceNotFoundException;
    
    Map<String, Boolean> deleteOrder(Integer orderId) throws ResourceNotFoundException;
}
