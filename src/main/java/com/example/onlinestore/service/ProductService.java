package com.example.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with ID " + id + " not found"));
    }

    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with ID " + id + " not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImage(product.getImage());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Product with ID " + id + " not found");
        } else {
            productRepository.deleteById(id);
        }
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public Long countTotalProducts() {
        return productRepository.countTotalProducts();
    }
}
