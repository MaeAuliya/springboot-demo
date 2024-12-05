package com.example.onlinestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinestore.entity.Transaction;
import com.example.onlinestore.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByCustomerId(Integer customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction accepTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setStatus("Accepted");
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public Transaction rejectTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setStatus("Rejected");
            return transactionRepository.save(transaction);
        }
        return null;
    }

}
