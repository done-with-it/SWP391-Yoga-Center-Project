package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "feedback_table")
public class Feedback implements Serializable{
    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackid;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customerid;
}
