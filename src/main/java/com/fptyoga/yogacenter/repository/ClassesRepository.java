package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Class;

@Repository
public interface ClassesRepository extends JpaRepository<Class, Long>{
    
}
