package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Orders")   // Table name changed to Orders
public class Order {

    private int id;

    @NotNull
    private String orderName;

    private double orderAmount;

    public Order() {
        super();
    }

    public Order(int id, String orderName, double orderAmount) {
        super();
        this.id = id;
        this.orderName = orderName;
        this.orderAmount = orderAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "order_name", nullable = false)
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Column(name = "order_amount", nullable = false)
    public double getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
