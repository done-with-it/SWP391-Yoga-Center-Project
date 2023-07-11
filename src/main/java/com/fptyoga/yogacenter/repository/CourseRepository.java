package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
