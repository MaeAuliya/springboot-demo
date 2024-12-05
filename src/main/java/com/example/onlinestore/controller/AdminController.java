package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.onlinestore.entity.Admin;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Transaction;
import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.service.AdminService;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.TransactionService;
import com.example.onlinestore.service.VoucherService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/admin/login")
    public String admin() {
        return "login-admin.html";
    }

    @GetMapping("/admin")
    public String adminHome(Model model) {
        List<Product> products = productService.getAllProducts();

        List<Voucher> vouchers = voucherService.getAllVouchers();

        List<Transaction> transactions = transactionService.getAllTransactions();

        model.addAttribute("products", products);
        model.addAttribute("vouchers", vouchers);
        model.addAttribute("transactions", transactions);

        return "admin.html";
    }

    @GetMapping("/admin/login/add")
    public String loginAdmin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Admin admin = adminService.login(email, password);
        if(admin != null) {
            model.addAttribute("message", "Login successful");
            session.setAttribute("adminId", admin.getId());
            return "redirect:/admin";
        } else {
            model.addAttribute("message", "Login failed");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/admin";
    }


    @GetMapping("/admin/voucher/delete/{id}")
    public String deleteVoucher(@PathVariable int id) {
        voucherService.deleteVoucher(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/voucher/edit/{id}")
    public String editVoucher(@PathVariable int id, @ModelAttribute Voucher voucher) {
        voucherService.updateVoucher(id, voucher);
        return "redirect:/admin";
    }

    @PostMapping("/admin/voucher/add")
    public String addVoucher(@ModelAttribute Voucher voucher) {
        voucherService.createVoucher(voucher);
        return "redirect:/admin";
    }

    @GetMapping("/admin/transaction/accept/{id}")
    public String acceptTransaction(@PathVariable int id) {
        transactionService.accepTransaction(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/transaction/reject/{id}")
    public String rejectTransaction(@PathVariable int id) {
        transactionService.rejectTransaction(id);
        return "redirect:/admin";
    }    
    
}