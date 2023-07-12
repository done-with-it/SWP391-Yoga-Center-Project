package com.fptyoga.yogacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Booking;
import com.fptyoga.yogacenter.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getSchedule(Long Customerid){
        return bookingRepository.findByCustomerid_UseridAndStatus(Customerid, true);
    }

    public List<Booking> getUserInClass(Long classid){
        return bookingRepository.findByClassid_ClassidAndStatus(classid, true);
    }
}
