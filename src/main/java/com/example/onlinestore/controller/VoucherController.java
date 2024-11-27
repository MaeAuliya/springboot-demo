package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.service.VoucherService;
import com.example.onlinestore.utils.ApiResponse;

@RestController
@RequestMapping("fp/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @PostMapping
    public ResponseEntity<ApiResponse<Voucher>> createVoucher(@RequestBody Voucher voucher) {
        Voucher newVoucher = voucherService.createVoucher(voucher);
        ApiResponse<Voucher> response = new ApiResponse<>("Voucher created successfully", newVoucher);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Voucher>>> getAllVouchers() {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        ApiResponse<List<Voucher>> response = new ApiResponse<>("Vouchers retrieved successfully", vouchers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Voucher>> getVoucherById(@PathVariable Integer id) {
        Voucher voucher = voucherService.getVoucherById(id);
        ApiResponse<Voucher> response = new ApiResponse<>("Voucher retrieved successfully", voucher);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<Voucher>>> getVouchersByCustomerId(@PathVariable Integer customerId) {
        List<Voucher> vouchers = voucherService.getVouchersByCustomerId(customerId);
        ApiResponse<List<Voucher>> response = new ApiResponse<>("Vouchers retrieved successfully", vouchers);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Voucher>> updateVoucher(@PathVariable Integer id, @RequestBody Voucher voucher) {
        Voucher updatedVoucher = voucherService.updateVoucher(id, voucher);
        ApiResponse<Voucher> response = new ApiResponse<>("Voucher updated successfully", updatedVoucher);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVoucher(@PathVariable Integer id) {
        voucherService.deleteVoucher(id);
        ApiResponse<String> response = new ApiResponse<>("Voucher deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/apply")
    public ResponseEntity<ApiResponse<Voucher>> putVoucherToCustomer(@RequestParam("voucherId") Integer voucherId, @RequestParam("customerId") Integer customerId) {
        Voucher voucher = voucherService.putVoucherToCustomer(voucherId, customerId);
        ApiResponse<Voucher> response = new ApiResponse<>("Voucher applied to customer successfully", voucher);
        return ResponseEntity.ok(response);
    }
}
