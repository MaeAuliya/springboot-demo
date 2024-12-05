package com.example.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Admin;
import com.example.onlinestore.repository.AdminRepository;
import com.example.onlinestore.utils.exception.BadRequestException;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        if (admin.getPhoneNumber() == null || admin.getName() == null || admin.getEmail() == null || admin.getPassword() == null) {
            throw new BadRequestException("Admin must have name, address, email, and phone number");
        } else {
            return adminRepository.save(admin);
        }
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Integer id, Admin admin) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin with ID " + id + " not found"));

        existingAdmin.setName(admin.getName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPhoneNumber(admin.getPhoneNumber());
        
        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Integer id) {
        if (adminRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Admin with ID " + id + " not found");
        } else {
            adminRepository.deleteById(id);
        }
    }

    public Admin login(String email, String password) {
        return adminRepository.login(email, password);
    }
}
