package com.example.onlinestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity", nullable = false)
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private int quantity;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "total_price", nullable = false)
    @Min(value = 0, message = "Total price must be greater than or equal to 0")
    private long totalPrice;

    public Order(){}

    public Order(int quantity, int productId, int customerId, long totalPrice){
        this.quantity = quantity;
        this.productId = productId;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomer(int customerId) {
        this.customerId = customerId;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
