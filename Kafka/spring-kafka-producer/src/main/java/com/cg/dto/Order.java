package com.cg.dto;


public class Order {
    private int orderId;
    private String productName;
    private int quantity;

    public Order() {}
    public Order(int orderId, String productName, int quantity) {
        this.orderId = orderId; this.productName = productName; this.quantity = quantity;
    }

    // getters/setters/toString
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", productName='" + productName + "', quantity=" + quantity + "}";
    }
}
