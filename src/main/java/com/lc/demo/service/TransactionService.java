package com.lc.demo.service;

import com.lc.demo.model.Customer;
import com.lc.demo.repositories.CustomerRepository;
import com.lc.demo.model.Transaction;
import com.lc.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final RewardsCalculator rewardsCalculator;
    private final CustomerRepository customerRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, RewardsCalculator rewardsCalculator, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.rewardsCalculator = rewardsCalculator;
        this.customerRepository = customerRepository;
    }

    public Transaction createTransaction(String customerId, Transaction transaction) {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        int rewardPoints = rewardsCalculator.calculateRewardPoints(transaction.getTransactionTotal());

        transaction.setTransactionDate(LocalDateTime.now());

        transaction.setTransactionRewardPoints(rewardPoints);

        transaction.setCustomerId(customerId);

        Transaction createdTransaction = transactionRepository.save(transaction);

        int currentPoints = customer.getRewardPoints();

        customer.setRewardPoints(currentPoints + rewardPoints);

        customerRepository.save(customer);

        return createdTransaction;
    }
}