package com.advisor.adtrans.repository;

import com.advisor.adtrans.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop,Long> {

}
