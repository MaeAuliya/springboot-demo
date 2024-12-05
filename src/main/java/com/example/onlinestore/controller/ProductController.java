package com.example.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.service.ProductService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Product> lastThreeProducts = products.subList(products.size() - 4, products.size());
        model.addAttribute("products", lastThreeProducts);
        return "index.html";
    }

    @GetMapping("/products-login")
    public String shop(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop-login.html";
    }

    @PostMapping("/shop/add")
    public String createProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        return "redirect:/products";
    }
  
    @GetMapping("/products") 
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop.html";
    } 

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model, HttpSession session) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "sproduct.html"; 
    } 

    @GetMapping("/product-login/{id}")
    public String getProductLoginById(@PathVariable Integer id, Model model, HttpSession session) {
        Product product = productService.getProductById(id);

        session.setAttribute("currentProductId", product.getId());

        model.addAttribute("product", product);
        return "sproduct-login.html";
    }

    @PostMapping("/shop/edit/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products/";
    }

    @PostMapping("/shop/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/shop/search")
    public String getProductsByName(@RequestParam String name, Model model) {
        List<Product> products = productService.getProductsByName(name);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/shop/count")
    public String countTotalProducts(Model model) {
        Long count = productService.countTotalProducts();
        model.addAttribute("count", count);
        return "count";
    }
}
