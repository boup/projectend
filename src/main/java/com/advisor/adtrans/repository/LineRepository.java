package com.advisor.adtrans.repository;

import com.advisor.adtrans.model.City;
import com.advisor.adtrans.model.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


@Repository
public interface LineRepository extends JpaRepository<Line,Long> {
//    Page<Line> findByCityId(Long cityID, Pageable pageable);
//
//    Optional<Line> findByIdAndCityId(Long Id,Long cityID);


    List<Line> findByCityId(Long cityid);
    //List<Line> findByLineId(Long cityid);
}
