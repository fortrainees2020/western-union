package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.entity.Order;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repository.OrderRepository;

import jakarta.validation.Valid;

@Service
public class OrderService implements IOrderService {	

    @Autowired 
    private OrderRepository orderRepo;
	
    @Override
    public List<Order> getOrdersFromDatabase() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepo.findById(id);
    }	
	
    @Override
    public Order createOrder(@Valid Order order) {
        return orderRepo.save(order);
    }

    @Override
    public ResponseEntity<Order> updateOrder(Integer orderId, @Valid Order changedOrder)
            throws ResourceNotFoundException {
        
        Order updatedOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not available: " + orderId));
        
        updatedOrder.setOrderName(changedOrder.getOrderName());
        updatedOrder.setOrderAmount(changedOrder.getOrderAmount());
        
        orderRepo.save(updatedOrder);
	
        return ResponseEntity.ok(updatedOrder);
    }

    @Override
    public Map<String, Boolean> deleteOrder(Integer orderId) throws ResourceNotFoundException {
        Order existingOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not available: " + orderId));
        
        orderRepo.delete(existingOrder);
        
        Map<String, Boolean> response  = new HashMap<>();
        response.put("Order has been deleted", Boolean.TRUE);
        
        return response;
    }
}
