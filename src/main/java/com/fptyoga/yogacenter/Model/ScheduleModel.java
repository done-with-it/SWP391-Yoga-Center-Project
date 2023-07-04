package com.fptyoga.yogacenter.Model;

import com.fptyoga.yogacenter.Entity.Class;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleModel {
    private Long schedule_id;
    private Class class_id;
    private Long time_id;
    private Long room_id;

}
