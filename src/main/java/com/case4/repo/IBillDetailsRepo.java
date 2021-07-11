package com.case4.repo;

import com.case4.model.Bill;
import com.case4.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillDetailsRepo extends JpaRepository<BillDetail, Long> {

    Iterable<BillDetail> findAllByBill(Bill bill);
}
