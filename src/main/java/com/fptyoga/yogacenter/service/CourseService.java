package com.fptyoga.yogacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Course;
import com.fptyoga.yogacenter.repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

     public Course getCourseById(Long courseid) {
        Optional<Course> userOptional = courseRepository.findById(courseid);
        return userOptional.orElse(null);
    }

}
