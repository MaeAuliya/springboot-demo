package com.example.onlinestore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User {
    public Admin() {}

    public Admin(String name, String address, String email, String password, String phoneNumber) {
        super(name, address, email, password, phoneNumber);
    }
}