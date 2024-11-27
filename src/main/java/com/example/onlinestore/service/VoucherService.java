package com.example.onlinestore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Customer;
import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.repository.CustomerRepository;
import com.example.onlinestore.repository.VoucherRepository;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Voucher createVoucher(Voucher voucher) {
        Customer customer = customerRepository.findById(4)
            .orElseThrow(() -> new NotFoundException("Customer with ID " + 4 + " not found"));
        voucher.setCustomer(customer);
        voucher.setCode("VCR-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0, 5));
        return voucherRepository.save(voucher);
    }

    public Voucher getVoucherById(Integer id) {
        return voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher with ID " + id + " not found"));
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public List<Voucher> getVouchersByCustomerId(Integer customerId) {
        return voucherRepository.findVoucherByCustomerId(customerId);
    }

    public Voucher putVoucherToCustomer(Integer voucherId, Integer customerId) {    
        Voucher voucher = voucherRepository.findById(voucherId)
            .orElseThrow(() -> new NotFoundException("Voucher with ID " + voucherId + " not found"));
        
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new NotFoundException("Customer with ID " + customerId + " not found"));
        
        voucher.setCustomer(customer);
        
        return voucherRepository.save(voucher);
    }
    

    public Voucher updateVoucher(Integer id, Voucher voucher) {
        Voucher existingVoucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher with ID " + id + " not found"));
        existingVoucher.setCode(voucher.getCode());
        existingVoucher.setDiscount(voucher.getDiscount());
        return voucherRepository.save(existingVoucher);
    }

    
    public void deleteVoucher(Integer id) {
        if (voucherRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Voucher with ID " + id + " not found"); 
        } else {
            voucherRepository.deleteById(id);
        }
    }
}