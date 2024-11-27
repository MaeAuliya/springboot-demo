package com.example.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.onlinestore.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT v FROM Voucher v WHERE v.customer.id = :customerId")      
    List<Voucher> findVoucherByCustomerId(int customerId);

    @Query("SELECT COUNT(v) FROM Voucher v")
    Long countTotalVouchers();
}
