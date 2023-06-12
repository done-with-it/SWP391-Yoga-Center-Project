package com.fptyoga.yogacenter.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseModel {
    private Long course_id;
    private String course_name;
    private String startdate;
    private String enddate;
    private boolean status;

}
