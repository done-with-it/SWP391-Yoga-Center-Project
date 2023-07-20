package com.fptyoga.yogacenter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Booking;
import com.fptyoga.yogacenter.dto.MonthlyTotal;
import com.fptyoga.yogacenter.repository.BookingRepository;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getSchedule(Long Customerid) {
        return bookingRepository.findByCustomerid_UseridAndStatus(Customerid, true);
    }

    public List<Booking> getUserInClass(Long classid) {
        return bookingRepository.findByClassid_ClassidAndStatus(classid, true);
    }

    public List<Booking> getHistorySchedule(Long Customerid) {
        return bookingRepository.findByCustomerid_UseridAndStatusAndResponseCode(Customerid, false, "00");
    }

    public Booking getCourse(Long classid) {
        Optional<Booking> booking = bookingRepository.findById(classid);
        return booking.orElse(null);
    }

    public List<MonthlyTotal> getMonthlyBookingAmount() {
        List<Object[]> results = bookingRepository.getMonthlyBookingAmount();
        List<MonthlyTotal> monthlyBookingAmounts = new ArrayList<>();

        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Long totalAmount = (Long) result[1];
            monthlyBookingAmounts.add(new MonthlyTotal(month, totalAmount));
        }

        return monthlyBookingAmounts;
    }

    public List<Booking> getRevenue(){
        return bookingRepository.findByResponseCode("00");
    }

    public long TotalAmount() {
        return bookingRepository.sumAmountWithResponseCode();
    }
}
