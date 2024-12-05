package com.example.onlinestore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Customer;
import com.example.onlinestore.repository.CustomerRepository;
import com.example.onlinestore.utils.exception.BadRequestException;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        if (customer.getPhoneNumber() == null || customer.getName() == null || customer.getEmail() == null || customer.getPassword() == null) {
            throw new BadRequestException("Customer must have name, address, email, and phone number");
        } else {
            customer.setOrders(new ArrayList<>());
            
            return customerRepository.save(customer);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + id + " not found"));
    }

    public Customer updateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + id + " not found"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Integer id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Customer with ID " + id + " not found");
        } else {
            customerRepository.deleteById(id);
        }
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public Long countTotalCustomers() {
        return customerRepository.countTotalCustomers();
    }

    public Customer login(String email, String password) {
        return customerRepository.login(email, password);
    }
}
