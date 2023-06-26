package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Schedule;
import com.fptyoga.yogacenter.Entity.User;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    List<User> findByRoom_Roomid(Long roomId);
    List<User> findByTime_Timeid(Long timeId);
    
}
