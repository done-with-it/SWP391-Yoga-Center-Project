package com.fptyoga.yogacenter.Model;

import com.fptyoga.yogacenter.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private Long user_id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String address;
    private String gender;
    private String phone;
    private String status;
    private String registration_date;
    private String img;
    private Role role;

}
