package com.fptyoga.yogacenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.Entity.Course;
import com.fptyoga.yogacenter.Entity.Trainer;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.ClassesRepository;
import com.fptyoga.yogacenter.repository.ContentRepository;
import com.fptyoga.yogacenter.repository.CourseRepository;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.ClassesService;
import com.fptyoga.yogacenter.service.ContentService;
import com.fptyoga.yogacenter.service.CourseService;
import com.fptyoga.yogacenter.service.TrainerService;
import com.fptyoga.yogacenter.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class homeController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public String show() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "1") int page) {
        List<User> trainList = userService.listAll(3L);
        PageRequest pageable = PageRequest.of(page - 1, 3, Sort.by("createdate").descending());
        Page<Content> contents = contentRepository.findAll(pageable);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        model.addAttribute("trainList", trainList);
        return "index";
    }

    // @GetMapping("/classes")
    // public String events(Model model, @RequestParam(defaultValue = "1") int page)
    // {
    // PageRequest pageable = PageRequest.of(page - 1, 6);
    // Page<Schedule> classes = scheduleRepository.findAll(pageable);
    // List<Course> courses = courseRepository.findAll();
    // List<User> trainList = userService.listAll(3L);
    // model.addAttribute("trainList", trainList);
    // model.addAttribute("courses", courses);
    // model.addAttribute("currentPage", page);
    // model.addAttribute("totalPages", classes.getTotalPages());
    // model.addAttribute("numofClasses", classes.getNumberOfElements());
    // model.addAttribute("totalClasses", classes.getTotalElements());
    // model.addAttribute("classes", classes);
    // return "classes";
    // }
    @GetMapping("/classes")
    public String getClass(Model model, @RequestParam(defaultValue = "") Long courseid,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String date) {
        List<String> classDistincts = classesService.getDistinctClass();
        PageRequest pageable = PageRequest.of(page - 1, 4);
        Page<Class> classes;

        if (courseid == null && date.isEmpty()) {
            classes = classesRepository.findAll(pageable);
            model.addAttribute("classes", classes);
        } else if (courseid != null && date.isEmpty()) {
            classes = classesService.getClassByCourseid(courseid, pageable);
            Long course = courseid;
            model.addAttribute("course", course);
            model.addAttribute("classes", classes);
        } else if (courseid == null && !date.isEmpty()) {
            classes = classesService.getSchedulesByDate(date, pageable);
            String dates = date;
            model.addAttribute("dates", dates);
            model.addAttribute("classes", classes);
        } else {
            classes = classesService.getSchedulesByCourseIdAndDate(courseid, date, pageable);
            model.addAttribute("course", courseid);
            model.addAttribute("dates", date);
            model.addAttribute("classes", classes);
        }
        List<Course> courses = courseRepository.findAll();

        model.addAttribute("classDistincts", classDistincts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", classes.getTotalPages());
        model.addAttribute("numofClasses", classes.getNumberOfElements());
        model.addAttribute("totalClasses", classes.getTotalElements());
        model.addAttribute("courses", courses);

        return "classes";
    }

    @GetMapping("/course-details")
    public String courseDetails(@RequestParam(defaultValue = "") Long courseid, Model model,
            @RequestParam(defaultValue = "1") int page) {
        Course courses = courseService.getCourseById(courseid);
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<Class> classes = classesService.getClassByCourseid(courseid, pageable);
        model.addAttribute("classes", classes);
        model.addAttribute("courses", courses);
        return "course-details";
    }

    @GetMapping("/adduser")
    public String addUser() {
        return "adduser";
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

    @GetMapping("/trainer")
    public String trainer(Model model) {
        List<User> trainList = userService.listAll(3L);
        model.addAttribute("trainList", trainList);
        return "trainer";
    }

    @GetMapping("/trainer-details")
    public String trainerdetails(@RequestParam("userid") Long userid, Model model) {
        User user = userService.getUser(userid);
        Trainer trainer = trainerService.getTrainer(userid);
        model.addAttribute("trainer", trainer);
        model.addAttribute("user", user);
        return "trainer-details";
    }

    @GetMapping("/schedule")
    public String events() {
        return "schedule";
    }

    @GetMapping("/event-details")
    public String eventdetails() {
        return "event-details";
    }

    @GetMapping("/courses")
    public String course(Model model) {
        List<Course> allCourses = courseService.getAllCourse();
        model.addAttribute("allCourses", allCourses);
        return "courses";
    }

    @GetMapping("/blog")
    public String blog(Model model, @RequestParam(defaultValue = "") String topic,
            @RequestParam(defaultValue = "1") int page) {

        Page<Content> contents;
        List<String> distinctTopics = contentService.getFilterContent();
        PageRequest pageable = PageRequest.of(page - 1, 6, Sort.by("contentid").descending());

        if (!topic.isEmpty()) {
            contents = contentService.getContent(topic, pageable);
            String topics = topic;
            model.addAttribute("topics", topics);
        } else {
            contents = contentRepository.findAll(pageable);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        model.addAttribute("distinctTopics", distinctTopics);

        return "blog";
    }

    // @GetMapping("/single-blog")
    // public String singleblog(@RequestParam("contentid") Long contentid, Model
    // model) {
    // Content content = contentService.getContentsBlog(contentid);
    // List<Content> listcontents =
    // contentRepository.findAll(Sort.by("contentid").descending()).subList(0, 3);
    // List<Content> contents = contentRepository.findAll();
    // List<String> distinctTopics = contentService.getFilterContent();
    // Content previousblog = contentService.getContentsBlog(contentid + 1);
    // Content nextblog = contentService.getContentsBlog(contentid - 1);
    // int flag = contents.size();
    // model.addAttribute("flag", flag);
    // model.addAttribute("listcontents", listcontents);
    // model.addAttribute("content", content);
    // model.addAttribute("previousblog", previousblog);
    // model.addAttribute("nextblog", nextblog);
    // model.addAttribute("distinctTopics", distinctTopics);
    // return "single-blog";
    // }

    @GetMapping("/single-blog")
    public String singleblog(@RequestParam("contentid") Long contentid, Model model) {
        List<Content> contents = contentRepository.findAll();
        int flag = contents.size();

        // Tìm content có contentid lớn hơn contentid đầu vào và trạng thái là true
        Long nextContentId = contentid - 1; // Adjust the initialization to contentid - 1
        Content nextContent = null;
        while (nextContent == null || !nextContent.isStatus()) {
            if (nextContentId < 1) { // Adjust the condition to check if nextContentId < 1
                break; // Nếu không còn content nào có contentid lớn hơn và trạng thái true thì thoát
                       // khỏi vòng lặp
            }
            nextContent = contentService.getContentsBlog(nextContentId);
            nextContentId--;
        }

        Content content = contentService.getContentsBlog(contentid);
        List<Content> listcontents = contentRepository.findAll(Sort.by("contentid").descending()).subList(0, 3);
        List<String> distinctTopics = contentService.getFilterContent();

        Content previousblog = null;
        Long previousContentId = contentid + 1; // Adjust the initialization to contentid + 1
        while (previousblog == null || !previousblog.isStatus()) {
            if (previousContentId > flag) { // Adjust the condition to check if previousContentId > flag
                break; // Nếu không còn content nào có contentid nhỏ hơn và trạng thái true thì thoát
                       // khỏi vòng lặp
            }
            previousblog = contentService.getContentsBlog(previousContentId);
            previousContentId++;
        }

        model.addAttribute("flag", flag);
        model.addAttribute("listcontents", listcontents);
        model.addAttribute("content", content);
        model.addAttribute("previousblog", previousblog);
        model.addAttribute("nextContent", nextContent);
        model.addAttribute("distinctTopics", distinctTopics);

        return "single-blog";
    }

    @GetMapping("/loginpage")
    public String loginpage(Model model) {
        model.addAttribute("user", new User());
        return "loginpage";
    }

    @PostMapping("/loginpage")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
            RedirectAttributes redirectAttributes, HttpSession session) {
        String error = "Error email or password!";
        if (email.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/loginpage";
        }
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/index";
        } else {
            // Sai thông tin đăng nhập, hiển thị thông báo lỗi
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/loginpage";
        }
    }

    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Get the current session
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Invalidate the session
            session.invalidate();
        }

        // Redirect to the login page or any other desired page
        return "redirect:/index";
    }

}
