package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Feedback;

@Repository
public interface FeedbacKRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByStatus(boolean status);

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.status = false")
    long countFeedbackByStatusFalse();
}
