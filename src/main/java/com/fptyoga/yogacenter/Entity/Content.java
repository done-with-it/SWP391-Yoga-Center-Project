package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Content_table")
public class Content implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentid;

    @Column(name = "topic")
    private String topic;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(name = "create_date")
    private LocalDate createdate;

    @Column(name = "status")
    private String status;

    @Column(name = "img")
    private String img;

    @JoinColumn(name = "Author")
    private String Author;

    // Getters and setters
}

