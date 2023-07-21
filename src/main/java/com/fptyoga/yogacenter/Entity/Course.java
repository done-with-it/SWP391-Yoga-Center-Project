package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_table")
public class Course implements Serializable {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;

    @Column(name = "course_name")
    private String coursename;

    @Column(name = "status")
    private boolean status;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @Column(name = "description", columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(name = "create_date")
    private LocalDate createdate;

    @Column(name = "price")
    @Min(value = 0, message = "Price must not be negative")
    private float price;

    @Column(name = "exchange")
    @Min(value = 0, message = "Price must not be negative")
    private float exchange;

    @Column(name = "duration")
    @NotBlank(message = "Duration must not be empty")
    private Long duration;

    // @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    // private Set<Class> class2;

}