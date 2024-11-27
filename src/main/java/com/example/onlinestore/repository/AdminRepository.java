package com.example.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinestore.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {}
