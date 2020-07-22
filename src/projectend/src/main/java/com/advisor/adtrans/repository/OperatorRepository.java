package com.advisor.adtrans.repository;

import com.advisor.adtrans.model.City;
import com.advisor.adtrans.model.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Long> {
//    Page<Operator> findByCityId(Long cityID, Pageable pageable);
//    Optional<Operator> findByIdAndCityId(Long Id, Long cityID);
       List<Operator> findByCityId(Long cityid);
}
