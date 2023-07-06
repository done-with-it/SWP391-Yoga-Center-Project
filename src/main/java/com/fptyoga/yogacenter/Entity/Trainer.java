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
@Table(name = "Trainer_table")
public class Trainer implements Serializable{
    
    @Id
    @Column(name = "info_id")
    private Long infoid;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainerid;

    @Column(name = "description", columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(name = "achievement")
    private String achievement;

    @Column(name = "experience")
    private float experience;

    @Column(name = "biography")
    private String biography;

}
