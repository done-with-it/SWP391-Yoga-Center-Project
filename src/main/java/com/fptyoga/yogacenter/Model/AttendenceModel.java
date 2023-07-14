package com.fptyoga.yogacenter.Model;

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
    private String attendance_date;
    private String note;
    private Long customer_id;
    private Long class_id;
}
