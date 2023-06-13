package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Time_table")
public class Time implements Serializable{
    @Id
    @Column(name = "time_id")
    private Long time_id;

    @Column(name = "start_time")
    private Time start_time;

    @Column(name = "end_time")
    private Time end_time;

}

