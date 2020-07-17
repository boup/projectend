package com.advisor.adtrans.controller;

import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.TravelBus;
import com.advisor.adtrans.repository.CityRepository;
import com.advisor.adtrans.repository.TravelBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableRowSorter;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")

public class TravelBusController {
    @Autowired
    private TravelBusRepository travelBusRepository;

    @Autowired
    private CityRepository cityRepository;

//    @GetMapping("/travelbus")
//    public List<TravelBus> getAllTravelBus() {
//        return travelBusRepository.findAll();
//    }


    @GetMapping("/city/{cityid}/travelbus")

    public List<TravelBus> getTransCityByCity(@PathVariable Long cityid) {

        if(!travelBusRepository.existsById(cityid)) {
            throw new ResourceNotFoundException("tran  city  not found!");
        }

        return travelBusRepository.findByCityId(cityid);
    }

}
