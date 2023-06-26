package com.fptyoga.yogacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(Long roleid){
        return userRepository.findByRole_Roleid(roleid);
    }

    public User getUser(Long userid){
        Optional<User> userOptional = userRepository.findById(userid);
        return userOptional.orElse(null);
    }

}
