package com.case4.repo;

import com.case4.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepo extends JpaRepository<Bill, Long> {


}
