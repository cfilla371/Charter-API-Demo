package com.lc.demo.service;

import com.lc.demo.model.Customer;
import com.lc.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public int getCustomerRewardPoints(String customerId) {
        return customerRepository.findById(customerId).map(Customer::getRewardPoints).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
    }
}