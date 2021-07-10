package com.case4.service.transaction;

import com.case4.model.Role;
import com.case4.model.Transaction;

import java.util.Optional;

public interface ITransactionService {

    Iterable<Transaction> findAll();

    Optional<Transaction> findById(Long id);

    void save(Transaction transaction);

    void remove(Long id);

    Transaction findByName(String name);
}
