package com.example.onlinestore.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Transaction;
import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.TransactionService;
import com.example.onlinestore.service.VoucherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/history")
    public String history(Model model, HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");
        
        List<Transaction> transactions = transactionService.getTransactionsByCustomerId(customerId);

        List<Order> transactionOrders = new ArrayList<Order>();
        List<Voucher> vouchers = new ArrayList<Voucher>();

        
        for (Transaction transaction : transactions) {
            List<Integer> orderIds = transaction.getOrderIds();
            Voucher voucher = voucherService.getVoucherById(transaction.getVoucherId());
            vouchers.add(voucher);
            for (Integer orderId : orderIds) {
                Order order = orderService.getOrderById(orderId);
                transactionOrders.add(order);
            }
        }

        List<Product> products = new ArrayList<Product>();
        for (Order order : transactionOrders) {
            Product product = productService.getProductById(order.getProductId());
            products.add(product);
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("transactionOrders", transactionOrders);
        model.addAttribute("products", products);
        model.addAttribute("vouchers", vouchers);

        return "history.html";
    }

    @PostMapping("/checkout")
    public String transaction(Model model, HttpSession session, @ModelAttribute Transaction transaction) {
        int customerId = (int) session.getAttribute("customerId");

        Transaction newTransaction = transaction;
        
        
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        List<Integer> orderIds = new ArrayList<Integer>();

        for (Order order : orders) {
            orderIds.add(order.getId());
        }
        // get date now
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 

        newTransaction.setDate(dtf.format(now).toString());
        newTransaction.setPrice(transaction.getPrice());
        newTransaction.setVoucherId(8);
        newTransaction.setStatus("pending");
        newTransaction.setCustomerId(customerId);
        newTransaction.setOrderIds(orderIds);

        transactionService.createTransaction(newTransaction);


        model.addAttribute("transaction", newTransaction);

        return "redirect:/history";
    }

    @PostMapping("/checkout-voucher")
    public String transactionVoucher(Model model, HttpSession session, @ModelAttribute Transaction transaction) {
        int customerId = (int) session.getAttribute("customerId");

        Transaction newTransaction = transaction;
        
        
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        List<Integer> orderIds = new ArrayList<Integer>();

        for (Order order : orders) {
            orderIds.add(order.getId());
        }
        // get date now
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 

        newTransaction.setDate(dtf.format(now).toString());
        newTransaction.setPrice(transaction.getPrice());
        newTransaction.setVoucherId(transaction.getVoucherId());
        newTransaction.setStatus("pending");
        newTransaction.setCustomerId(customerId);
        newTransaction.setOrderIds(orderIds);

        transactionService.createTransaction(newTransaction);


        model.addAttribute("transaction", newTransaction);

        return "redirect:/history";
    }


}
