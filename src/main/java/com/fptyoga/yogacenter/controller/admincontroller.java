package com.fptyoga.yogacenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fptyoga.yogacenter.Entity.Schedule;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.ScheduleRepository;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.UserService;

@Controller
@RequestMapping("/admin")
public class admincontroller {
    
    
    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/index")
    public String trainer(Model model, @RequestParam(defaultValue = "4") Long roleid) {
        List<User> trainList = userService.listAll(roleid);
        List<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        model.addAttribute("trainList", trainList);
        return "admin/index";
    }
    @GetMapping("/401")
    public String show401(){
        return "admin/401";
    }
    @GetMapping("/404")
    public String show404(){
        return "admin/404";
    }
    @GetMapping("/500")
    public String show500(){
        return "admin/500";
    }
}
