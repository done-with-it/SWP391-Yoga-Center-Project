package com.fptyoga.yogacenter.Model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeModel {
    private Long time_id;
    private Time start_time;
    private Time end_time;

}
