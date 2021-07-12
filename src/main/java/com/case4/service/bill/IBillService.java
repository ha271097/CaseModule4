package com.case4.service.bill;

import com.case4.model.Bill;
import com.case4.model.Cart;
import com.case4.model.Product;

import java.util.HashMap;
import java.util.Optional;

public interface IBillService {

    Iterable<Bill> findAll();

    Optional<Bill> findById(Long id);

    void save(Bill bill);

    void remove(Long id);



    public int addBills(Bill bill);

    public void addBillDetails(HashMap<Long, Cart> cartHashMap);
}
