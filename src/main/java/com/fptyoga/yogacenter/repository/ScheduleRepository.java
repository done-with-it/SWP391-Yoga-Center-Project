package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fptyoga.yogacenter.Entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
}
