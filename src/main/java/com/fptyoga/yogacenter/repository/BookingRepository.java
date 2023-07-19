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

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.classid.classid = :classid AND b.customerid.userid = :userid AND b.classid.date = :date AND b.classid.timeid.timeid = :timeid AND b.status = true")
    boolean existsByUserIdAndClassidDateAndTimeIdAndStatus(Long userid, String date, Long timeid, Long classid);

    @Query("SELECT b FROM Booking b WHERE b.classid.classid = :classid AND b.customerid.userid = :userid AND b.classid.date = :date AND b.classid.timeid.timeid = :timeid AND b.status = true")
    Booking findByUserIdAndClassidDateAndTimeIdAndStatus(Long userid, String date, Long timeid, Long classid);

    @Modifying
    @Query("UPDATE Booking b SET b.status = false WHERE b.bookingdate < :endDate AND b.status = true")
    void updateStatusAfter2Minutes(LocalDateTime endDate);

    boolean existsByClassid_ClassidAndCustomerid_Userid(Long classid, Long customerid);

    Booking findByClassid_ClassidAndCustomerid_Userid(Long classid, Long customerid);

    @Query("SELECT MONTH(b.bookingdate) AS month, SUM(b.amount) AS totalAmount " +
            "FROM Booking b " +
            "WHERE b.responseCode = '00'" +
            "GROUP BY MONTH(b.bookingdate)")
    List<Object[]> getMonthlyBookingAmount();

    List<Booking> findByResponseCode(String responsecode);

}
