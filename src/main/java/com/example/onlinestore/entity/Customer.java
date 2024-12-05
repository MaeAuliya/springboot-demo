package com.example.onlinestore.entity;

import java.util.List;


import jakarta.persistence.*;

@Entity 
@Table(name = "customer")
public class Customer extends User {
    @Column(name = "order_ids", nullable = true)
    private List<Integer> orderIds;

    public Customer() {}

    public Customer(String name, String email, String password, String phoneNumber, List<Integer> orderIds) {
        super(name, email, password, phoneNumber);
        this.orderIds = (orderIds == null) ? List.of() : orderIds;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrders(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
}
