package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{
    
}
