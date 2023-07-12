package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerid_UseridAndStatus(Long customerid, boolean status);

    List<Booking> findByClassid_ClassidAndStatus(Long classid, boolean status);

}
