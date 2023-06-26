package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_table")
public class Course implements Serializable {
    
    @Id
    @Column(name = "course_id")
    private Long courseid;

    @Column(name = "course_name")
    private String coursename;

    @Column(name = "status")
    private boolean status;
    
    // @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    // private Set<Class> class2;

}
