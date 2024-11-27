package com.example.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinestore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
