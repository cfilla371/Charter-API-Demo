package com.lc.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.lc.demo.model.Transaction;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>  {
}
