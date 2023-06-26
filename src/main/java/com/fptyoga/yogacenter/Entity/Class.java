package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Class_table")
public class Class implements Serializable{
    
    @Id
    @Column(name = "class_id")
    private Long classid;

    @Column(name = "class_name")
    private String classname;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseid;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private User coachid;

    // @OneToMany(mappedBy = "class1", cascade = CascadeType.ALL)
    // private Set<Attendance> attendance;
}