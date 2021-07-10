package com.case4.service.transaction;

import com.case4.model.Transaction;
import com.case4.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionRepo transactionRepo;
    @Override
    public Iterable<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Transaction findByName(String name) {
        return null;
    }
}
