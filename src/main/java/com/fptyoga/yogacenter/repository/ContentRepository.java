package com.fptyoga.yogacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.Content;
import java.util.List;


@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByTopic(String topic);
}
