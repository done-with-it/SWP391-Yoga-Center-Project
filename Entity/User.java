package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User implements Serializable{
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;

    @Column(name = "img")
    private String img;

    @Column(name = "registration_date")
    private String registration_date;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private Set<Class> class1;
    
    // @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    // private Set<Feedback> feedbacks;
}
