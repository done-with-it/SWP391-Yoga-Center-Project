package com.fptyoga.yogacenter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Trainer;
import com.fptyoga.yogacenter.repository.TrainerRepository;

@Service
public class TrainerService {
    
    @Autowired
    private TrainerRepository repo;

    public Trainer getTrainer(Long trainerid){
        return repo.findByTrainerid_Userid(trainerid);
    }

    public Long getInfoidByUserid(Long id) {
        return repo.findInfoidByUserid(id);
    }
}
