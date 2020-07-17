package com.advisor.adtrans.controller;


import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.City;
import com.advisor.adtrans.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class CityController {


    @Autowired
    private CityRepository cityRepository;


    @GetMapping("/city")
    //need to be discuss with the teacher
//    public Page<City> getAllCity(Pageable pageable) {
//        return cityRepository.findAll((org.springframework.data.domain.Pageable) pageable);
//    }
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @GetMapping("/city/{id}")
//    public City retrieveCity(@PathVariable long id) {
//        Optional<City> city = cityRepository.findById(id);
//
//        if (!city.isPresent())
//            throw new ResourceNotFoundException("id-" + id);
//
//        return city.get();}
//}
    public City getCityByID(@PathVariable Long id) {
        Optional<City> optStudent = cityRepository.findById(id);
        if (optStudent.isPresent()) {
            return optStudent.get();
        } else {
            throw new ResourceNotFoundException("City not found with id " + id);
        }
    }
}
