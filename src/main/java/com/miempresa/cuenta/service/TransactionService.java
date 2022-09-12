package com.miempresa.cuenta.service;

import com.miempresa.cuenta.entity.Transaction;
import com.miempresa.cuenta.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> transactionList() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransaction(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public boolean existsByTransactionId(int transactionId) {
        return transactionRepository.existsById(transactionId);
    }
}
