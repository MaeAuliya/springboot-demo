package com.example.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.repository.OrderRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.utils.exception.NotFoundException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public void deleteOrder(Integer id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Order with ID " + id + " not found");
        } else {
            orderRepository.deleteById(id); 
        }
    }

    public Order updateOrder(Integer id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with ID " + id + " not found"));

        existingOrder.setQuantity(order.getQuantity());
        
        return orderRepository.save(existingOrder);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with ID " + id + " not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void minusQuantity(Integer id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with ID " + id + " not found"));

        Product product = productRepository.findById(existingOrder.getProductId())
                .orElseThrow(() -> new NotFoundException("Product with ID " + existingOrder.getProductId() + " not found"));

                
        
        if (existingOrder.getQuantity() < 2) {
            orderRepository.deleteById(id);
        } else {
            existingOrder.setQuantity(existingOrder.getQuantity() - 1);
            existingOrder.setTotalPrice(existingOrder.getQuantity() * product.getPrice());
            orderRepository.save(existingOrder);
        }
    }

    public void plusQuantity(Integer id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with ID " + id + " not found"));

        Product product = productRepository.findById(existingOrder.getProductId())
                .orElseThrow(() -> new NotFoundException("Product with ID " + existingOrder.getProductId() + " not found"));

        if(existingOrder.getQuantity() < product.getStock()) {
            existingOrder.setQuantity(existingOrder.getQuantity() + 1);
            existingOrder.setTotalPrice(existingOrder.getQuantity() * product.getPrice());
            orderRepository.save(existingOrder);
        }
    }

    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Long getTotalPriceOrder(Integer customerId) {
        Long totalPrice = orderRepository.getTotalPriceOrder(customerId);

        if(totalPrice == null) {
            return 0L;
        } else {
            return totalPrice;
        }
    }

    public void deleteByCustomerId(Integer customerId) {
        orderRepository.deleteByCustomerId(customerId);
    }
}
