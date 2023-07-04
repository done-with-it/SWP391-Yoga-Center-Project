package com.fptyoga.yogacenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fptyoga.yogacenter.Entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s JOIN s.classid c WHERE c.courseid.courseid = :courseId")
    Page<Schedule> findAllByCourseId(@Param("courseId") Long courseId, Pageable page);

    @Query("SELECT s FROM Schedule s JOIN s.classid c WHERE c.date = :date")
    Page<Schedule> findByDate(@Param("date") String date, Pageable page);

    @Query("SELECT s FROM Schedule s JOIN s.classid c WHERE c.trainerid = :trainerid")
    Page<Schedule> findByTrainer(@Param("trainerid") String trainerid, Pageable page);

    @Query("SELECT s FROM Schedule s JOIN s.classid c WHERE c.courseid.courseid = :courseId AND c.date = :date")
    Page<Schedule> findByCourseIdAndDate(@Param("courseId") Long courseId, @Param("date") String date, Pageable page);
}
