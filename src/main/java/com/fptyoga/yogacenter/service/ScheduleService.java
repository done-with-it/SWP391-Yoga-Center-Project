package com.fptyoga.yogacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Schedule;
import com.fptyoga.yogacenter.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll();

    }
}
