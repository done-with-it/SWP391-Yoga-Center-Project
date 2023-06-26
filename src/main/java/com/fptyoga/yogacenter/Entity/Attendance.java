package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance_table")
public class Attendance implements Serializable{
    
    @Id
    @Column(name = "attendance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceid;

    @Column(name = "attendance_date")
    private String attendancedate;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customerid;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classid;


}