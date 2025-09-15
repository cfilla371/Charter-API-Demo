package com.lc.demo.controller;

import com.lc.demo.model.Transaction;
import com.lc.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/by-customer/{customerId}")
    public ResponseEntity<Transaction> createTransaction(@PathVariable String customerId, @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(customerId, transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

}