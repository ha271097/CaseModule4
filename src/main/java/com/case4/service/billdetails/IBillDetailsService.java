package com.case4.service.billdetails;

import com.case4.model.Bill;
import com.case4.model.BillDetail;
import com.case4.model.Cart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public interface IBillDetailsService {

    Iterable<BillDetail> findAll();

    Optional<BillDetail> findById(Long id);

    void save(BillDetail billDetail);

    void remove(Long id);

    Iterable<BillDetail> findBillDetailByBills(Bill bill);


}
