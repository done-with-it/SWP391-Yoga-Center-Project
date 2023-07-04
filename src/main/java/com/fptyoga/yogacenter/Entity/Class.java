package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Class_table")
public class Class implements Serializable{
    
    @Id
    @Column(name = "class_id")
    private Long classid;

    @Column(name = "class_name")
    private String classname;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseid;


    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainerid;

    // @OneToMany(mappedBy = "class1", cascade = CascadeType.ALL)
    // private Set<Attendance> attendance;
}