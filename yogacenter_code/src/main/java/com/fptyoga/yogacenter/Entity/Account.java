package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_table")
public class Account implements Serializable{

    @Id
    @Column(name = "username")
    private String account_id;
    
    @Column(name = "password")
    private String password ;

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;
    

}
