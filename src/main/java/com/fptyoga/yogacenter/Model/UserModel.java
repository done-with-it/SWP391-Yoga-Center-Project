package com.fptyoga.yogacenter.Model;

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
    private Long role;

    public UserModel(String fullname, String email, String phone, String img) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.img = img;
    }

    

    

}
