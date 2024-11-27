package com.example.onlinestore.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Voucher> vouchers;

    public Customer() {}

    public Customer(String name, String address, String email, String password, String phoneNumber, List<Order> order, List<Voucher> vouchers) {
        super(name, address, email, password, phoneNumber);
        this.orders = (order == null) ? List.of() : order;
        this.vouchers = (vouchers == null) ? List.of() : vouchers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> order) {
        this.orders = order;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
    
}
