package com.fptyoga.yogacenter.Model;

import java.time.LocalTime;

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
public class TimeModel {
    private Long timeId;
    private LocalTime startTime;
    private LocalTime endTime;

}
