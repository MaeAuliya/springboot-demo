package com.example.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.onlinestore.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.customerId = ?1")
    List<Order> findByCustomerId(Integer customerId);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.customerId = ?1")
    Long getTotalPriceOrder(Integer customerId);

    @Query("DELETE FROM Order o WHERE o.customerId = ?1")
    void deleteByCustomerId(Integer customerId);
}
