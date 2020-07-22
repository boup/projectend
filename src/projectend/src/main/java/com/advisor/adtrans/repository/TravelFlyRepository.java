package com.advisor.adtrans.repository;

import com.advisor.adtrans.model.TravelFly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TravelFlyRepository  extends JpaRepository<TravelFly,Long> {

        List<TravelFly> findByCityId(Long cityid);
}
