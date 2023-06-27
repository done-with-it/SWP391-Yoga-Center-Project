package com.fptyoga.yogacenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.Entity.Schedule;
import com.fptyoga.yogacenter.Entity.Trainer;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.ContentRepository;
import com.fptyoga.yogacenter.service.ContentService;
import com.fptyoga.yogacenter.service.ScheduleService;
import com.fptyoga.yogacenter.service.TrainerService;
import com.fptyoga.yogacenter.service.UserService;

@Controller
@RequestMapping("")
public class homeController {

    @GetMapping("")
    public String show() {
        return "redirect:/index";
    }

    // @Autowired
    // private ScheduleService scheduleService;

    // @GetMapping("/events")
    // public String events(Model eventModel) {
    // List<Schedule> eventList = scheduleService.getAllSchedule();
    // eventModel.addAttribute("eventList", eventList);
    // return "events";
    // }

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/classes")
    public String events(Model eventModel) {
        List<Schedule> classes = scheduleService.getAllSchedule();
        eventModel.addAttribute("classes", classes);
        return "classes";
    }

    @GetMapping("/classes-details")
    public String classesdetails() {
        return "classes-details";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home(Model model) {
        List<User> trainList = userService.listAll(3l);
        model.addAttribute("trainList", trainList);
        return "index";
    }

    @GetMapping("/trainer")
    public String trainer(Model model) {
        List<User> trainList = userService.listAll(3l);
        model.addAttribute("trainList", trainList);
        return "trainer";
    }

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/trainer-details")
    public String trainerdetails(@RequestParam("userid") Long userId, Model model) {
        User user = userService.getUser(userId);
        Trainer trainer = trainerService.getTrainer(userId);
        model.addAttribute("trainer", trainer);
        model.addAttribute("trainList", user);
        return "trainer-details";
    }

    @GetMapping("/event-details")
    public String eventdetails() {
        return "event-details";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/blog")
    public String blog(Model model, @RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 3);
        Page<Content> contents = contentRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        return "blog";
    }

    @Autowired
    private ContentService contentService;

    @GetMapping("/single-blog")
    public String singleblog(@RequestParam("contentid") Long contentid, Model model) {
        Content content = contentService.getContent(contentid);
        model.addAttribute("content", content);
        return "single-blog";
    }

    @GetMapping("/loginpage")
    public String loginpage() {
        return "loginpage";
    }

}
