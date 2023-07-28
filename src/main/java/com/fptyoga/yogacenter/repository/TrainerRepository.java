package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fptyoga.yogacenter.Entity.Trainer;



public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByTrainerid_Userid(Long trainerid);
    @Query("SELECT t.infoid FROM Trainer t WHERE t.trainerid.userid = :id")
    Long findInfoidByUserid(Long id);
}
