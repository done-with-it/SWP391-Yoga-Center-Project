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
    private Long contentId;
    private String topic;
    private String description;
    private LocalDate createDate;
    private String status;
    private String img;
    private User user;

}
