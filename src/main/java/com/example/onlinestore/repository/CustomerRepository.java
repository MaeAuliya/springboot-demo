package com.example.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.onlinestore.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameContaining(String name);

    @Query("SELECT COUNT(c) FROM Customer c")
    Long countTotalCustomers();

    @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.password = ?2")
    Customer login(String email, String password);
} 
