package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinestore.entity.Admin;
import com.example.onlinestore.service.AdminService;
import com.example.onlinestore.utils.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("fp/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<ApiResponse<Admin>> createAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.createAdmin(admin);
        ApiResponse<Admin> response = new ApiResponse<>("Admin created successfully", newAdmin);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Admin>>> getAllAdmin() {
        List<Admin> adminList = adminService.getAllAdmins();
        ApiResponse<List<Admin>> response = new ApiResponse<>("Admin retrieved successfully", adminList);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Admin>> updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        ApiResponse<Admin> response = new ApiResponse<>("Admin updated successfully", updatedAdmin);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        ApiResponse<String> response = new ApiResponse<String>("Admin deleted successfully", "Success");

        return ResponseEntity.ok(response);
    }
    
}