package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Content_table")
public class Content implements Serializable{
    @Id
    @Column(name = "content_id")
    private Long content_id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDate create_date;

    @Column(name = "status")
    private String status;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff_id;

    // Getters and setters
}

