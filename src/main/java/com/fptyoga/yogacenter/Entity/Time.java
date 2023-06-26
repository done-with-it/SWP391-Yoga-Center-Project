package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Time_table")
public class Time implements Serializable{
    @Id
    @Column(name = "time_id")
    private Long timeid;

    @Column(name = "start_time")
    private String starttime;

    @Column(name = "end_time")
    private String endtime;

}

