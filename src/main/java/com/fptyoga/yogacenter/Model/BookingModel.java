package com.fptyoga.yogacenter.Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingModel {

    private Long booking_id;
    private LocalDate booking_date;
    private Long customer_id;
    private Long class_id;
    private String status;

}