package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinestore.entity.Customer;
import com.example.onlinestore.service.CustomerService;
import com.example.onlinestore.utils.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("fp/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        ApiResponse<Customer> response = new ApiResponse<>("Customer created successfully", newCustomer);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        ApiResponse<List<Customer>> response = new ApiResponse<>("Customers retrieved successfully", customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        ApiResponse<Customer> response = new ApiResponse<>("Customer retrieved successfully", customer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        ApiResponse<Customer> response = new ApiResponse<>("Customer updated successfully", updatedCustomer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        ApiResponse<String> response = new ApiResponse<String>("Customer deleted successfully", "Success");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Customer>>> getCustomersByName(@RequestParam("name") String name) {
        List<Customer> customers = customerService.getCustomersByName(name);
        ApiResponse<List<Customer>> response = new ApiResponse<>("Customers retrieved successfully", customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countTotalCustomers() {
        Long totalCustomers = customerService.countTotalCustomers();
        ApiResponse<Long> response = new ApiResponse<>("Total customers retrieved successfully", totalCustomers);
        return ResponseEntity.ok(response);
    }
}
