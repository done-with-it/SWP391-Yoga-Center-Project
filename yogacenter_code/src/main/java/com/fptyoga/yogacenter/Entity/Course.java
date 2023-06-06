package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_table")
public class Course implements Serializable {
    
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "startdate")
    private String startdate;

    @Column(name = "enddate")
    private String enddate;

    // @Column(name = "class_id")
    // private String class_id;

    @Column(name = "status")
    private boolean status;
    
    // @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    // private Set<Class> class2;

}
