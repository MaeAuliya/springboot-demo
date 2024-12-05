package com.example.onlinestore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.onlinestore.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT COUNT(v) FROM Voucher v")
    Long countTotalVouchers();

    @Query("SELECT v FROM Voucher v WHERE v.code = ?1")
    Voucher findByCode(String code);
}
