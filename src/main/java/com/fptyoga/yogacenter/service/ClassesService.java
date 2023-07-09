package com.fptyoga.yogacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.repository.ClassesRepository;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;

    public  List<String> getDistinctClass(){
        return classesRepository.findDistinctDate();
    }

    public Page<Class> getClassByCourseid(Long courseid, Pageable page) {
        return classesRepository.findByCourseid_Courseid(courseid, page);
    }

    public Page<Class> getSchedulesByDate(String date, Pageable page) {
        return classesRepository.findByDate(date, page);
    }

    public Page<Class> getSchedulesByTrainer(String trainerid, Pageable page) {
        return classesRepository.findByTrainerid(trainerid, page);
    }

    public Page<Class> getSchedulesByCourseIdAndDate(Long courseId, String date, Pageable page) {
        return classesRepository.findByCourseidAndDate(courseId, date, page);
    }

}
