package com.fptyoga.yogacenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Schedule;
import com.fptyoga.yogacenter.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    // public List<Schedule> getAllSchedule(){
    //     return scheduleRepository.findAll();
    // }


    public Page<Schedule> getSchedulesByCourseId(Long courseid, Pageable page) {
        return scheduleRepository.findAllByCourseId(courseid, page);
    }

    public Page<Schedule> getSchedulesByDate(String date, Pageable page) {
        return scheduleRepository.findByDate(date, page);
    }

    public Page<Schedule> getSchedulesByTrainer(String trainerid, Pageable page) {
        return scheduleRepository.findByTrainer(trainerid, page);
    }

    public Page<Schedule> getSchedulesByCourseIdAndDate(Long courseId, String date, Pageable page) {
        return scheduleRepository.findByCourseIdAndDate(courseId, date, page);
    }

}