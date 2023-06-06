package com.fptyoga.yogacenter.Model;

import java.util.Date;

import com.fptyoga.yogacenter.Entity.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackModel {
    private Long feedback_id;
    private String content;
    private String status;
    private Date date;
    private User user_fb;

}
