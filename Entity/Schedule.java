package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Schedule_table")
public class Schedule implements Serializable{
    @Id
    @Column(name = "schedule_id")
    private Long schedule_Id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class class_id;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time_id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room_id;

}
