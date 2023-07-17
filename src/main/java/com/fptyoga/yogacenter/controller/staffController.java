package com.fptyoga.yogacenter.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fptyoga.yogacenter.Entity.Booking;
import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.Entity.Course;
import com.fptyoga.yogacenter.Entity.Feedback;
import com.fptyoga.yogacenter.Entity.Room;
import com.fptyoga.yogacenter.Entity.Time;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.BookingRepository;
import com.fptyoga.yogacenter.repository.ClassesRepository;
import com.fptyoga.yogacenter.repository.ContentRepository;
import com.fptyoga.yogacenter.repository.CourseRepository;
import com.fptyoga.yogacenter.repository.FeedbacKRepository;
import com.fptyoga.yogacenter.repository.RoomRepository;
import com.fptyoga.yogacenter.repository.TimeRepository;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.BookingService;
import com.fptyoga.yogacenter.service.ClassesService;
import com.fptyoga.yogacenter.service.ContentService;
import com.fptyoga.yogacenter.service.CourseService;
import com.fptyoga.yogacenter.service.FeedbackService;
import com.fptyoga.yogacenter.service.UserService;

@Controller
@RequestMapping("/staff")
public class staffController {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FeedbacKRepository feedbacKRepository;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/indexClass")
    public String Class(Model model) {
        List<Class> classes = classesRepository.findAll();
        model.addAttribute("classes", classes);
        return "staff/indexClass";
    }

    @GetMapping("/index")
    public String blog(Model model, @RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 20, Sort.by("contentid").descending());
        Page<Content> contents = contentService.getAllContentsByStatus(pageable);

        List<Class> classes = classesRepository.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("contents", contents);
        return "staff/index";
    }

    @GetMapping("/indexCourse")
    public String Course(Model model) {
        List<Course> courses = courseRepository.findAll();

        List<Class> classes = classesRepository.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("courses", courses);
        return "staff/indexCourse";
    }

    @GetMapping("/download-png")
    public ResponseEntity<Resource> downloadPng(@RequestParam(defaultValue = "") Long contentid) {
        byte[] pngData = contentService.getPngDataById(contentid);
        if (pngData != null) {
            ByteArrayResource resource = new ByteArrayResource(pngData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=image.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        }
        // Xử lý trường hợp tệp tin không tồn tại
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/addblog")
    public String addUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        Content content;
        if (id == null || id == 0) {
            content = new Content();
            model.addAttribute("id", 0); // Add this line to explicitly set the ID as 0
        } else {
            content = contentRepository.findById(id).orElse(null);
            model.addAttribute("id", id);
        }
        model.addAttribute("content", content);
        return "staff/addblog";
    }

    @PostMapping("/content")
    public String setBlog(@ModelAttribute Content content,
            RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {

        content.setCreatedate(LocalDate.now());
        content.setStatus(true);
        contentRepository.save(content);
        try {
            contentService.saveContent(file, content);
        } catch (IOException e) {
            // Xử lý lỗi nếu cần
        }
        redirectAttributes.addFlashAttribute("message", "The Blog has been saved successfully.");
        return "redirect:/staff/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid content Id:" + id));
        content.setStatus(false);
        contentRepository.save(content);
        return "redirect:/staff/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditBlog(@PathVariable("id") Long id, Model model) {
        try {
            Content content = contentRepository.findById(id).orElse(null);
            model.addAttribute("content", content);
            return "staff/edit";
        } catch (Exception e) {
        }
        return "staff/edit";
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        List<Course> courses = courseRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<Time> times = timeRepository.findAll();
        List<User> trainers = userService.listAll(3L);
        List<String> classDistincts = classesService.getDistinctClass();
        model.addAttribute("classes", new Class());
        model.addAttribute("courses", courses);
        model.addAttribute("rooms", rooms);
        model.addAttribute("times", times);
        model.addAttribute("trainers", trainers);
        model.addAttribute("classDistincts", classDistincts);
        return "staff/addClass";
    }

    @PostMapping("/addClass/new")
    public String newClass(@ModelAttribute Class classes, RedirectAttributes ra) {
        classes.setStatus(true);
        classesRepository.save(classes);
        ra.addFlashAttribute("message", "The Class has been saved successfully.");
        return "redirect:/staff/indexClass";
    }

    @GetMapping("/deleteClass/{id}")
    public String deleteClass(@PathVariable("id") Long id) {
        Class classes = classesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid class Id:" + id));
        classes.setStatus(false);
        classesRepository.save(classes);
        return "redirect:/staff/indexClass";
    }

    @GetMapping("/editClasses/{id}")
    public String showEditClass(@PathVariable("id") Long id, Model model) {
        List<Course> courses = courseRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<Time> times = timeRepository.findAll();
        List<User> trainers = userService.listAll(3L);
        List<String> classDistincts = classesService.getDistinctClass();
        model.addAttribute("courses", courses);
        model.addAttribute("rooms", rooms);
        model.addAttribute("times", times);
        model.addAttribute("trainers", trainers);
        model.addAttribute("classDistincts", classDistincts);
        try {
            Class classes = classesRepository.findById(id).orElse(null);
            model.addAttribute("classes", classes);
            return "staff/editClass";
        } catch (Exception e) {
        }
        return "staff/editClass";
    }

    @GetMapping("/addCourse")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "staff/addCourse";
    }

    @PostMapping("/addCourse/new")
    public String newCourse(@ModelAttribute Course course, RedirectAttributes ra,
            @RequestParam("file") MultipartFile file) {
        course.setCreatedate(LocalDate.now());
        course.setStatus(true);
        try {
            courseService.saveCourse(file, course);
        } catch (IOException e) {
            // Xử lý lỗi nếu cần
        }
        ra.addFlashAttribute("message", "The Course has been saved successfully.");
        return "redirect:/staff/indexCourse";
    }

    @GetMapping("/downloads-png")
    public ResponseEntity<Resource> downloadPngCourse(@RequestParam(defaultValue = "") Long courseid) {
        byte[] pngData = courseService.getPngDataById(courseid);
        if (pngData != null) {
            ByteArrayResource resource = new ByteArrayResource(pngData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=image.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        }
        // Xử lý trường hợp tệp tin không tồn tại
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/deletecourse/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        course.setStatus(false);
        courseRepository.save(course);
        return "redirect:/staff/indexCourse";
    }

    @GetMapping("/editcourse")
    public String showEditCourse(@RequestParam(defaultValue = "") Long id, Model model) {
        try {
            Course course = courseRepository.findById(id).orElse(null);
            model.addAttribute("course", course);
            return "staff/editcourse";
        } catch (Exception e) {
        }
        return "staff/editCourse";
    }

    @GetMapping("/classBooking")
    private String ClassBooking(Model model, @RequestParam(name = "classid", required = false) Long classid) {
        List<Booking> booked = bookingService.getUserInClass(classid);
        Class classes = classesRepository.findById(classid).orElse(null);

        List<Class> classesList = classesRepository.findAll();
        model.addAttribute("classesList", classesList);

        model.addAttribute("classes", classes);
        model.addAttribute("booked", booked);
        return "staff/classBooking";
    }

    @GetMapping("/deleteClassBooking/{id}")
    public String deleteUserInClassBooking(@PathVariable("id") Long bookingid, RedirectAttributes redirectAttributes) {
        if (bookingid != null) {
            Booking booking = bookingRepository.findById(bookingid)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + bookingid));
            Long classid = booking.getClassid().getClassid();
            if (booking != null) {
                booking.setStatus(false);
                bookingRepository.save(booking);
            }
            redirectAttributes.addAttribute("classid", classid);
        }
        return "redirect:/staff/classBooking";
    }

    @GetMapping("/viewRequest")
    public String viewRequest(Model model, @RequestParam(defaultValue = "") Boolean status) {
        if (status != null) {
            if (status == true) {
                List<Feedback> feedbacks = feedbackService.getFeedbackByStatusTrue();
                model.addAttribute("feedbacks", feedbacks);
            } else {
                List<Feedback> feedbacks = feedbackService.getFeedbackByStatusFalse();
                model.addAttribute("feedbacks", feedbacks);
            }
        }

        List<Class> classes = classesRepository.findAll();
        model.addAttribute("classes", classes);

        return "staff/viewRequest";
    }

    @GetMapping("/accept/{id}")
    public String acceptRequest(@PathVariable("id") Long id) {
        Feedback feedback = feedbacKRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feedback Id:" + id));
        feedback.setStatus(true);
        feedbacKRepository.save(feedback);
        return "redirect:/staff/viewRequest";
    }

}
