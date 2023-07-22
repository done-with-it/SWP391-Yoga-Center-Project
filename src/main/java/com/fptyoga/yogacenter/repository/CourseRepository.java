package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

    @Query("SELECT COUNT(c.courseid) FROM Course c WHERE c.status = true")
    int CountCourseWithStatusTrue();
}
