package com.case4.service.bill;

import com.case4.model.Bill;
import com.case4.model.Cart;
import com.case4.repo.IBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
public class BillService implements IBillService{

    @Autowired
    private IBillRepo billRepo;

    @Override
    public Iterable<Bill> findAll() {
        return billRepo.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepo.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepo.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepo.deleteById(id);
    }

    @Override
    public int addBills(Bill bill) {
        return 0;
    }

    @Override
    public void addBillDetails(HashMap<Long, Cart> cartHashMap) {

    }
}
