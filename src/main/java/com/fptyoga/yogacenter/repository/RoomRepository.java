package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    
}
