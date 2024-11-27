package com.example.onlinestore.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @CollectionTable(name = "transaction_order", joinColumns = @JoinColumn(name = "transaction_id"))
    private List<Integer> orderIds;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "price", nullable = false)
    private double price;

    public Transaction() {}

    public Transaction(List<Integer> orderIds, String date, double price) {
        this.orderIds = orderIds;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
