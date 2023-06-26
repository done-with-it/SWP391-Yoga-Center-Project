package com.fptyoga.yogacenter.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.repository.ContentRepository;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

   public List<Content> listAllContent(){
        return contentRepository.findAll();
    }

    public Content getContent(Long contentid){
        Optional<Content> contents = contentRepository.findById(contentid);
        return contents.orElse(null);

    }
}
