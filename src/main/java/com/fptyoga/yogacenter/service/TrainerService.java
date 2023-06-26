package com.fptyoga.yogacenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Trainer;
import com.fptyoga.yogacenter.repository.TrainerRepository;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer getTrainer(Long userid){
        Optional<Trainer> trainerOptional = trainerRepository.findById(userid);
        return trainerOptional.orElse(null);
    }
}
