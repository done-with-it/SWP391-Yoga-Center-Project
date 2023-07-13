package com.fptyoga.yogacenter.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerid_UseridAndStatus(Long customerid, boolean status);

    List<Booking> findByCustomerid_UseridAndStatusAndResponseCode(Long customerid, boolean status, String responseCode);

    List<Booking> findByClassid_ClassidAndStatus(Long classid, boolean status);

    @Query("SELECT COUNT(b.classid) FROM Booking b WHERE b.status = true AND b.classid.classid = :classId")
    int countDuplicateClassIdsWithStatusTrue(@Param("classId") Long classId);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.customerid.userid = :userid AND b.classid.date = :date AND b.classid.timeid.timeid = :timeid AND b.status = true")
    boolean existsByUserIdDateAndTimeIdAndStatus(Long userid, String date, Long timeid);

    @Modifying
    @Query("UPDATE Booking b SET b.status = false WHERE b.bookingdate < :endDate AND b.status = true")
    void updateStatusAfter2Minutes(LocalDateTime endDate);

}
