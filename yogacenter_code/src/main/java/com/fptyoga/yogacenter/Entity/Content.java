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
    private Long contentId;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "createdate")
    private LocalDate createDate;

    @Column(name = "status")
    private String status;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}

