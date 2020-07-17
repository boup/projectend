package com.advisor.adtrans.controller;


import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.TravelBus;
import com.advisor.adtrans.model.TravelFly;
import com.advisor.adtrans.repository.CityRepository;
import com.advisor.adtrans.repository.TravelBusRepository;
import com.advisor.adtrans.repository.TravelFlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class TravelFlyController {

    @Autowired
    private TravelFlyRepository travelFlyRepository;

    @Autowired
    private CityRepository cityRepository;

//    @GetMapping("/travelfly")
//    public List<TravelFly> getAllTravelFly() {
//        return travelFlyRepository.findAll();
//    }



    @GetMapping("/city/{cityid}/travelfly")

    public List<TravelFly> getTransCityByCity(@PathVariable Long cityid) {

        if(!travelFlyRepository.existsById(cityid)) {
            throw new ResourceNotFoundException("tran  city  not found!");
        }

        return travelFlyRepository.findByCityId(cityid);
    }

}
