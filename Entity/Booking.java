package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Booking_table")
public class Booking implements Serializable{
    @Id
    @Column(name = "booking_id")
    private Long booking_id;

    @Column(name = "booking_date")
    private LocalDate booking_date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer_id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class class_id;

    @Column(name = "status")
    private String status;

}