package com.fptyoga.yogacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Feedback;
import com.fptyoga.yogacenter.repository.FeedbacKRepository;
@Service
public class FeedbackService {
    @Autowired
    private FeedbacKRepository feedbacKRepository;

    public List<Feedback> getFeedbackByStatusTrue(){
        return feedbacKRepository.findByStatus(true);
    }
    public List<Feedback> getFeedbackByStatusFalse(){
        return feedbacKRepository.findByStatus(false);
    }
}
