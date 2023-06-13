package com.fptyoga.yogacenter.Model;

import java.time.LocalDate;
import java.util.Date;

import com.fptyoga.yogacenter.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackModel {
    private Long feedback_id;
    private String content;
    private String status;
    private LocalDate date;
    private Long customer_id;

}
