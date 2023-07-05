package com.fptyoga.yogacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.repository.ClassesRepository;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;

    public  List<String> getDistinctClass(){
        return classesRepository.findDistinctDate();
    }

}
