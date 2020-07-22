package com.advisor.adtrans.repository;

import com.advisor.adtrans.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long>
{

}

