package com.fptyoga.yogacenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Content;


@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    @Query("SELECT DISTINCT c.topic FROM Content c")
    List<String> findDistinctTopics();

    Page<Content> findByTopicAndStatusOrderByCreatedateDesc(String topic, boolean status, Pageable page);

    Page<Content> findAllByStatusOrderByCreatedateDesc(boolean status, Pageable page);
}
