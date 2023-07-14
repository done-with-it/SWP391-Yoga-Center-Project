package com.fptyoga.yogacenter.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassModel {
    private Long class_id;
    private String class_name;
    private String status;
    private Long course_id;
    private Long coach_id;
    private Long customer_id;

}