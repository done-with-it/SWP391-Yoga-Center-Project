package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "user_table")
public class User implements Serializable{


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(50)")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "address", columnDefinition = "NVARCHAR(MAX)")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private boolean status;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "registration_date")
    private LocalDate registrationdate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private Set<Class> class1;
    
    // @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    // private Set<Feedback> feedbacks;
}
