package com.fptyoga.yogacenter.Model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeModel {
    private Long timeId;
    private LocalTime startTime;
    private LocalTime endTime;

}
