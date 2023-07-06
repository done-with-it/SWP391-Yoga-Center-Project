package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Class;

@Repository
public interface ClassesRepository extends JpaRepository<Class, Long>{

    @Query("SELECT  DISTINCT c.date FROM Class c")
    List<String> findDistinctDate();

    Page<Class> findByCourseid_Courseid(Long courseid, Pageable page);

    Page<Class> findByDate(String date, Pageable page);

    Page<Class> findByTrainerid(String trainerid, Pageable page);

    Page<Class> findByCourseidAndDate(Long courseId, String date, Pageable page);

}
