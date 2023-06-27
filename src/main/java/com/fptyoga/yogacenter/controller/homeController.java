package com.fptyoga.yogacenter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.fptyoga.yogacenter.repository.ScheduleRepository;
import com.fptyoga.yogacenter.service.ContentService;
import com.fptyoga.yogacenter.service.ScheduleService;
import com.fptyoga.yogacenter.service.TrainerService;
import com.fptyoga.yogacenter.service.UserService;

@Controller
@RequestMapping("")
public class homeController {

    @GetMapping("")
    public String show(){
        return"redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "1") int page) {
        List<User> trainList = userService.listAll(3L);
        PageRequest pageable = PageRequest.of(page-1, 3, Sort.by("createdate").descending());
        Page<Content> contents = contentRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        model.addAttribute("trainList", trainList);
        return "index";
    }

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/classes")
    public String events(Model eventModel, @RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page-1, 6);
        Page<Schedule> classes = scheduleRepository.findAll(pageable);
        eventModel.addAttribute("currentPage", page);
        eventModel.addAttribute("totalPages", classes.getTotalPages());
        eventModel.addAttribute("numofClasses", classes.getNumberOfElements());
        eventModel.addAttribute("totalClasses", classes.getTotalElements());
        eventModel.addAttribute("classes", classes);
        return "classes";
    }

    @GetMapping("/classes-details")
    public String classesdetails() {
        return "classes-details";
    }

    @GetMapping("/about")
    public String about(Model model) {
        List<User> trainList = userService.listAll(3L);
        model.addAttribute("trainList", trainList);
        return "about";
    }

    // @GetMapping("/trainer")
    // public String trainer(){
    // return "trainer";
    // }
    @Autowired
    private UserService userService;

    @GetMapping("/trainer")
    public String trainer(Model model) {
        List<User> trainList = userService.listAll(3L);
        model.addAttribute("trainList", trainList);
        return "trainer";
    }

    // @GetMapping("/trainer")
    // public List<User> trainer(Model model){
    // Long roleid = 2L;
    // List<User> trainList = userService.listAll(roleid);
    // return trainList;
    // }
    @Autowired
    private TrainerService trainerService;


    @GetMapping("/trainer-details")
    public String trainerdetails(@RequestParam("userid") Long userid, Model model) {
        User user = userService.getUser(userid);
        Trainer trainer = trainerService.getTrainer(userid);
        model.addAttribute("trainer", trainer);
        model.addAttribute("user", user);
        return "trainer-details";
    }

    

    @GetMapping("/events")
    public String events() {
        return "events";
    }

    @GetMapping("/event-details")
    public String eventdetails() {
        return "event-details";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    // @GetMapping("/blog")
    // public String blog() {
    //     return "blog";
    // }
    // @Autowired
    
    @Autowired
    private ContentService contentService;

    // @GetMapping("/blog")
    // public String blog(Model model) {
    //     List<Content> contents = contentService.listAllContent();
    //     model.addAttribute("contents", contents);
    //     return "blog";
    // }
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/blog")
    public String blog(Model model, @RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page-1, 3, Sort.by("createdate").descending());
        Page<Content> contents = contentRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        return "blog";
    }

    @GetMapping("/single-blog")
    public String singleblog(@RequestParam("contentid") Long contentid, @RequestParam(defaultValue = "1") int page,Model model) {
        Content content = contentService.getContent(contentid);
        PageRequest pageable = PageRequest.of(page-1, 3, Sort.by("createdate").descending());
        Page<Content> listcontents = contentRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listcontents.getTotalPages());
        model.addAttribute("listcontents", listcontents);
        model.addAttribute("content", content);
        return "single-blog";
    }

    @GetMapping("/loginpage")
    public String loginpage() {
        return "loginpage";
    }

}
