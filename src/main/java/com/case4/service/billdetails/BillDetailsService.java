package com.case4.service.billdetails;

import com.case4.model.Bill;
import com.case4.model.BillDetail;
import com.case4.model.Cart;
import com.case4.repo.IBillDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class BillDetailsService implements IBillDetailsService {

    @Autowired
    private IBillDetailsRepo billDetailsRepo;

    @Override
    public Iterable<BillDetail> findAll() {
        return billDetailsRepo.findAll();
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return billDetailsRepo.findById(id);
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailsRepo.save(billDetail);
    }

    @Override
    public void remove(Long id) {
        billDetailsRepo.deleteById(id);
    }

    @Override
    public Iterable<BillDetail> findBillDetailByBills(Bill bill) {
        return billDetailsRepo.findAllByBill(bill);
    }

}
