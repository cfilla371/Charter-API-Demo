package com.lc.demo.controller;

import com.lc.demo.model.Customer;
import com.lc.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/reward-points")
    public ResponseEntity<Integer> getCustomerRewardPoints(@PathVariable String customerId) {
        try {
            int rewardPoints = customerService.getCustomerRewardPoints(customerId);
            return new ResponseEntity<>(rewardPoints, HttpStatus.OK);
        } catch (RuntimeException ex) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}