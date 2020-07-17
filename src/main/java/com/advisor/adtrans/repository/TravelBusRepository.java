package com.advisor.adtrans.repository;


import com.advisor.adtrans.model.TravelBus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelBusRepository extends JpaRepository<TravelBus,Long> {

    List<TravelBus> findByCityId(Long cityid);
}
