package com.fptyoga.yogacenter.Model;

import java.time.LocalDate;

import com.fptyoga.yogacenter.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Content {
    private Long content_id;
    private String topic;
    private String description;
    private LocalDate create_date;
    private String status;
    private String img;
    private Long staff_id;

}
