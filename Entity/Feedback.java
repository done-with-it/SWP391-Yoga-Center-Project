package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "feedback_table")
public class Feedback implements Serializable{
    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedback_id;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "Customer_id")
    private User Customer_id;
}
