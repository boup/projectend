package com.advisor.adtrans.controller;

import com.advisor.adtrans.exception.ResourceNotFoundException;
import com.advisor.adtrans.model.Line;
import com.advisor.adtrans.model.Ticket;
import com.advisor.adtrans.repository.CityRepository;

import com.advisor.adtrans.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class TicketController {


    // @Autowired(required=false)

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CityRepository cityRepository;
//
//    @GetMapping("/ticket")
//    public List<Ticket> getAllTicket() {
//        return ticketRepository.findAll();
//    }


    @GetMapping("/city/{cityid}/ticket")

    public List<Ticket> getTicketByCity(@PathVariable Long cityid) {

        if(!ticketRepository.existsById(cityid)) {
            throw new ResourceNotFoundException("ticket not found!");
        }

        return ticketRepository.findByCityId(cityid);
    }

//    public Page<Ticket> getTicketByCityID (@PathVariable(value = "cityID") Long cityID,
//                                                Pageable pageable) {
//        return ticketRepository.findByCityId(cityID, pageable);
//    }


}
