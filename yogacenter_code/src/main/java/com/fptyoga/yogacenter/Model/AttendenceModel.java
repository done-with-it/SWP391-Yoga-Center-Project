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
public class AttendenceModel {
    private Long attendance_id;
    private String user_name;
    private String address;
    private Class class_attd;

}
