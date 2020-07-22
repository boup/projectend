package com.advisor.adtrans.controller;


import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.City;
import com.advisor.adtrans.model.Stop;
import com.advisor.adtrans.repository.CityRepository;
import com.advisor.adtrans.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StopController {


    @Autowired
    private StopRepository stopRepository;

    @GetMapping("/stop")
    public List<Stop> getAllStop() {
        return stopRepository.findAll();
    }

    @GetMapping("/stop/{id}")
    public Stop getStopByID(@PathVariable Long id) {
        Optional<Stop> optSt = stopRepository.findById(id);
        if (optSt.isPresent()) {
            return optSt.get();
        } else {
            throw new ResourceNotFoundException("stop not found with id " + id);
        }
    }
}
