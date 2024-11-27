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

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.utils.ApiResponse;

@RestController
@RequestMapping("fp/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        ApiResponse<Product> response = new ApiResponse<>("Product created successfully", newProduct);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = new ApiResponse<>("Products retrieved successfully", products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        ApiResponse<Product> response = new ApiResponse<>("Product retrieved successfully", product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        ApiResponse<Product> response = new ApiResponse<>("Product updated successfully", updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        ApiResponse<String> response = new ApiResponse<>("Product deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByName(name);
        ApiResponse<List<Product>> response = new ApiResponse<>("Products retrieved successfully", products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countTotalProducts() {
        Long count = productService.countTotalProducts();
        ApiResponse<Long> response = new ApiResponse<>("Total products retrieved successfully", count);
        return ResponseEntity.ok(response);
    }
}
