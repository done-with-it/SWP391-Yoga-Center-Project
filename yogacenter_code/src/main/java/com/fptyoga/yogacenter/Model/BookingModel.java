package com.fptyoga.yogacenter.Model;

import java.time.LocalDate;

import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.Entity.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingModel {
    private Long bookingId;
    private LocalDate bookingDate;
    private User user;
    private Class class_book;
    private String status;

}
