package com.advisor.adtrans.controller;

import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.Line;
import com.advisor.adtrans.repository.CityRepository;
import com.advisor.adtrans.repository.LineRepository;
import org.hibernate.annotations.LazyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

//import java.awt.print.Pageable;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class LineController {

    //@Autowired(required=false)

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private CityRepository cityRepository;

//    @GetMapping("/line")
//    public List<Line> getAllLine() {
//        return lineRepository.findAll();
//    }

    @GetMapping("/city/{cityid}/line")
    public List<Line> getLineByCity(@PathVariable Long cityid) {

        if(!lineRepository.existsById(cityid)) {
            throw new ResourceNotFoundException("line not found!");
        }

        return lineRepository.findByCityId(cityid);
    }

//    public Page<Line> getAllLineByCityId(@PathVariable(value = "cityID") Long cityID, Pageable pageable) {
//        return lineRepository.findByCityId(cityID, pageable);
//    }
   // @GetMapping("/students/{studentId}/assignments")

}
