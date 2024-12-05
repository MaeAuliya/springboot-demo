package com.example.onlinestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Voucher;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.VoucherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/cart")
    public String chart(Model model, HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");
        model.addAttribute("customerId", customerId);

        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        List<Product> products = new ArrayList<>();

        for (Order order : orders) {
            Product product = productService.getProductById(order.getProductId());
            products.add(product);
        }

        Long totalPrice = orderService.getTotalPriceOrder(customerId);

        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);

        return "cart.html";
    }

    @GetMapping("/cart-voucher")
    public String cartVoucher(Model model, HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");
        model.addAttribute("customerId", customerId);

        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        List<Product> products = new ArrayList<>();

        for (Order order : orders) {
            Product product = productService.getProductById(order.getProductId());
            products.add(product);
        }

        Long totalPrice = orderService.getTotalPriceOrder(customerId);

        Voucher voucher = (Voucher) session.getAttribute("voucher");

        if(totalPrice > 0){
            totalPrice = Math.round(totalPrice - (totalPrice * voucher.getDiscount() / 100.0));
        } else {
            totalPrice = 0L;
        }

        model.addAttribute("voucher", voucher);
        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);

        return "cart-voucher.html";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "redirect:/history";
    }

    @PostMapping("/cart-add")
    public String addToCart(HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");

        int currentProductId = (int) session.getAttribute("currentProductId");

        Product currentProduct = productService.getProductById(currentProductId);

        List<Order> existingOrders = orderService.getOrdersByCustomerId(customerId);

        for (Order order : existingOrders) {
            if (order.getProductId() == currentProductId) {
                orderService.plusQuantity(order.getId());
                return "redirect:/cart";
            } else {
                continue;
            }
        }
        
        Order newOrder = new Order();

        newOrder.setCustomer(customerId);
        newOrder.setProductId(currentProductId);
        newOrder.setQuantity(1);
        newOrder.setTotalPrice(currentProduct.getPrice());

        orderService.createOrder(newOrder);
        return "redirect:/cart";
    }

    @GetMapping("/cart/minus-quantity/{id}")
    public String minusQuantity(@PathVariable Integer id, HttpSession session) {
        orderService.minusQuantity(id);
        if(session.getAttribute("voucher") != null) {
            return "redirect:/cart-voucher";
        } else {
            return "redirect:/cart";
        }
    }

    @GetMapping("/cart/plus-quantity/{id}")
    public String plusQuantity(@PathVariable Integer id, HttpSession session) {
        orderService.plusQuantity(id);
        if(session.getAttribute("voucher") != null) {
            return "redirect:/cart-voucher";
        } else {
            return "redirect:/cart";
        }
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, HttpSession session) {
        orderService.deleteOrder(id);
        if(session.getAttribute("voucher") != null) {
            return "redirect:/cart-voucher";
        } else {
            return "redirect:/cart";
        }
    }

    @PostMapping("/apply-voucher")
    public String applyVoucher(@RequestParam String code, Model model, HttpSession session) {
        Voucher voucher = voucherService.getVoucherByCode(code);

        if (voucher == null) {
            model.addAttribute("message", "Voucher not found");
            return "redirect:/cart";
        } else {
            model.addAttribute("message", "Voucher applied");
            session.setAttribute("voucher", voucher);
            return "redirect:/cart-voucher";
        }
    }
}
