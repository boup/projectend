package com.advisor.adtrans.controller;


import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.Line;
import com.advisor.adtrans.model.Operator;
import com.advisor.adtrans.repository.CityRepository;
import com.advisor.adtrans.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class OperatorController {

    @Autowired
    //@Autowired(required=false)
    private OperatorRepository operatorRepository;

@Autowired
    private CityRepository cityRepository;

//    @GetMapping("/operator")
// public List<Operator> getAllOperator() {
//        return operatorRepository.findAll();
//    }


@GetMapping("/city/{cityid}/operator")

public List<Operator> getOperatorByCity(@PathVariable Long cityid) {
    if(!operatorRepository.existsById(cityid)) {
        throw new ResourceNotFoundException("city not found!");
    }
    return operatorRepository.findByCityId(cityid);
  }
//    public Page<Operator> getAllOperatorByCityID (@PathVariable(value = "cityID") Long cityID,
//                                                  Pageable pageable) {
//    return operatorRepository.findByCityId(cityID, pageable);
//    }


}
