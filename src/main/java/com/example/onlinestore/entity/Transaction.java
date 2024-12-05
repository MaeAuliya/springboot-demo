package com.example.onlinestore.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_ids", nullable = false)
    private List<Integer> orderIds;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "voucher_id", nullable = true)
    private int voucherId;

    public Transaction() {}

    public Transaction(List<Integer> orderIds, String date, long price, int customerId, String status, int voucherId) {
        this.orderIds = orderIds;
        this.date = date;
        this.price = price;
        this.customerId = customerId;
        this.status = status;
        this.voucherId = voucherId;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }
}
