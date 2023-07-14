package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fptyoga.yogacenter.Entity.Time;

public interface TimeRepository extends JpaRepository<Time, Long>{
    
}
