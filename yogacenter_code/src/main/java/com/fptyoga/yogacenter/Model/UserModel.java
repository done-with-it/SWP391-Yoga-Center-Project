package com.fptyoga.yogacenter.Model;

import com.fptyoga.yogacenter.Entity.Role;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private Long user_id;
    private String user_name;
    private String email;
    private String address;
    private String gender;
    private String phone;
    private String status;
    private String img;
    private Role role;

}
