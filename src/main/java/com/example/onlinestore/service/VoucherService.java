package com.example.onlinestore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.repository.VoucherRepository;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public Voucher createVoucher(Voucher voucher) {
        voucher.setCode("VCR-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0, 5));
        return voucherRepository.save(voucher);
    }

    public Voucher getVoucherById(Integer id) {
        return voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher with ID " + id + " not found"));
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher updateVoucher(Integer id, Voucher voucher) {
        Voucher existingVoucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher with ID " + id + " not found"));
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

    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }
}