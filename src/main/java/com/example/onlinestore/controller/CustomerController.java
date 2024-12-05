package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.onlinestore.entity.Customer;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.service.CustomerService;
import com.example.onlinestore.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/account")
    public String account(Model model, HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");
        model.addAttribute("customerId", customerId);

        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);

        return "account.html";
    }

    @GetMapping("/customer")
    public String home(Model model, HttpSession session) {
        List<Product> products = productService.getAllProducts();
        List<Product> lastThreeProducts = products.subList(products.size() - 4, products.size());
        model.addAttribute("products", lastThreeProducts);

        int customerId = (int) session.getAttribute("customerId");
        model.addAttribute("customerId", customerId);

        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);

        return "home-login.html";
    }

    @GetMapping("/customer/login") 
    public String login() {
        return "login-user.html";
    }

    @GetMapping("/customer/register")
    public String register() {
        return "register.html"; 
    }

    @GetMapping("/customer/login/add")
    public String loginCustomer(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        Customer customer = customerService.login(email, password);
        if(customer != null) {
            model.addAttribute("message", "Login successful");
            session.setAttribute("customerId", customer.getId());
            return "redirect:/customer";
        } else {
            model.addAttribute("message", "Login failed");
            return "redirect:/";
        }
    }

    @PostMapping("/customer/add")
    public String createCustomer(@ModelAttribute("customerInfo") Customer customer, Model model) {
        Customer newCustomer = customerService.createCustomer(customer);
        if(newCustomer != null) {
            model.addAttribute("message", 
            "Resgistration successful, You can Login Now!!");
            return "redirect:/customer/login";
        } else {
            model.addAttribute("message", "Registration failed!!");
            return "redirect:/customer/register";
        }
        
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/get-customer/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer";
    }

    @PostMapping("/customer/edit/{id}")
    public String updateCustomer(@PathVariable Integer id, @ModelAttribute("newCustomer") Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customer";
    }

    @PostMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/customer/search")
    public String getCustomersByName(@RequestParam("name") String name, Model model) {
        List<Customer> customers = customerService.getCustomersByName(name);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customer/count")
    public String countTotalCustomers(Model model) {
        Long totalCustomers = customerService.countTotalCustomers();
        model.addAttribute("totalCustomers", totalCustomers);
        return "totalCustomers";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("customerId");
        model.addAttribute("message", "Logout successful");
        return "redirect:/";
    }
    
}
