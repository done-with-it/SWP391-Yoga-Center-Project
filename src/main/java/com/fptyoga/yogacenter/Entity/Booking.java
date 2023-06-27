package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Booking_table")
public class Booking implements Serializable{
    @Id
    @Column(name = "booking_id")
    private Long bookingid;

    @Column(name = "booking_date")
    private LocalDate bookingdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customerid;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classid;

    @Column(name = "status")
    private boolean status;

}