package com.fptyoga.yogacenter.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import com.fptyoga.yogacenter.Entity.Role;
import com.fptyoga.yogacenter.Entity.Trainer;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.Validator.EmailValidationUtil;
import com.fptyoga.yogacenter.repository.BookingRepository;
import com.fptyoga.yogacenter.repository.ClassesRepository;
import com.fptyoga.yogacenter.repository.ContentRepository;
import com.fptyoga.yogacenter.repository.CourseRepository;
import com.fptyoga.yogacenter.repository.FeedbacKRepository;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.AccountService;
import com.fptyoga.yogacenter.service.BookingService;
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

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FeedbacKRepository feedbacKRepository;

    @Autowired
    private AccountService accountService;

    // @GetMapping("")
    // public String show() {
    // return "redirect:/index";
    // }

    @GetMapping
    public String home(Model model, RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal OAuth2User oa2User, HttpSession session) {

        if (oa2User != null && userRepository.existsByEmail(oa2User.getAttribute("email"))) {
            String email = oa2User.getAttribute("email");
            User user = userRepository.findByEmail(email);
            session.setAttribute("user", user);
        } else if (oa2User != null && !userRepository.existsByEmail(oa2User.getAttribute("email"))) {
            String email = oa2User.getAttribute("email");
            String name = oa2User.getAttribute("name");
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFullname(name);
            newUser.setPassword("0000");
            Role role = new Role();
            role.setRoleid(4l);
            newUser.setRole(role);
            userRepository.save(newUser);
            session.setAttribute("user", newUser);
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    // @PostMapping("/login")
    // public String login(HttpSession session){
    // String email = (String) session.getAttribute("email");
    // if (userRepository.existsByEmail(email)) {
    // User user = userRepository.findByEmail(email);
    // session.setAttribute("user", user);
    // }
    // return "index";
    // }

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, HttpSession session) {
        List<User> trainList = userService.listAll(3L);
        PageRequest pageable = PageRequest.of(page - 1, 3, Sort.by("createdate").descending());
        Page<Content> contents = contentRepository.findAll(pageable);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        model.addAttribute("trainList", trainList);
        model.addAttribute("feedbacks", new Feedback());
        return "index";
    }

    @GetMapping("/class-details")
    public String showInfoClass(Model model, @RequestParam(defaultValue = "classid") Long classid) {
        List<Booking> booked = bookingService.getUserInClass(classid);
        Class classes = classesRepository.findById(classid).orElse(null);
        model.addAttribute("classes", classes);
        model.addAttribute("booked", booked);
        return "class-details";
    }

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
    public String getSchedules(@RequestParam("userid") Long userid, @RequestParam("status") int status, Model model,
            @RequestParam("roleid") Long roleid,
            @RequestParam(defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        if (roleid == 4) {
            List<Booking> booking = bookingRepository.findAll();
            for (Booking book : booking) {
                LocalDateTime expired = book.getExpired();
                if (expired != null && LocalDateTime.now().isAfter(expired)) {
                    book.setStatus(false);
                    bookingRepository.save(book);
                }
            }
            List<Booking> booked;
            if (status == 1 || status == 3) {
                booked = bookingService.getSchedule(userid);
            } else {
                booked = bookingService.getHistorySchedule(userid);
                Collections.sort(booked, Comparator.comparing(Booking::getBookingid));
                Collections.reverse(booked);
            }

            model.addAttribute("booked", booked);
        } else {
            Page<Class> classes = classesService.getSchedulesByTrainer(userid, pageable);
            model.addAttribute("classes", classes);
        }
        model.addAttribute("status", status);
        model.addAttribute("roleid", roleid);
        return "schedule";
    }

    @PostMapping("/request")
    public String viewRequest(@ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes) {
        feedback.setStatus(false);
        feedback.setDate(LocalDate.now());
        feedbacKRepository.save(feedback);
        return "redirect:/index";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", new User());
        return "profile";
    }

    @PostMapping("/profile/edit")
    public String newUser(@ModelAttribute User user, RedirectAttributes ra, @RequestParam("file") MultipartFile file) {
        userRepository.save(user);
        try {
            userService.saveUser(file, user);
        } catch (IOException e) {
            // Xử lý lỗi nếu cần
        }
        ra.addFlashAttribute("update", "The user has been update successfully.");
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        try {
            User user = userRepository.findById(id).orElse(null);
            model.addAttribute("user", user);
            return "profile";
        } catch (Exception e) {
        }
        return "profile";
    }

    @GetMapping("/download-png")
    public ResponseEntity<Resource> downloadPng(@RequestParam(defaultValue = "") Long userid) {
        byte[] pngData = userService.getPngDataById(userid);
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
            contents = contentService.getAllContentsByStatus(pageable);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contents.getTotalPages());
        model.addAttribute("contents", contents);
        model.addAttribute("distinctTopics", distinctTopics);

        return "blog";
    }

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

    @GetMapping("/createAccount")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "createAccount";
    }

    @PostMapping("/createAccount/new")
    public String createnewUser(@ModelAttribute User user, RedirectAttributes ra,
            @RequestParam(defaultValue = "4") Role roleid,
            @RequestParam("confirmPassword") String confirmPassword, HttpSession session) {

        boolean isValid = EmailValidationUtil.isValidEmail(user.getEmail());
        if (!isValid) {
            ra.addFlashAttribute("existsemail", "The Email not valid");
            return "redirect:/createAccount";
        } else {
            if (!user.getPassword().equals(confirmPassword)) {
                ra.addFlashAttribute("passwordMismatch", "Passwords do not match");
                return "redirect:/createAccount";
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                ra.addFlashAttribute("existsemail", "The Email already exists.");
                return "redirect:/createAccount";
            }

            user.setRegistrationdate(LocalDate.now());
            user.setStatus(false);
            user.setRole(roleid);
            userRepository.save(user);
            session.setAttribute("email", user.getEmail());
            accountService.sendVerificationEmail(user.getEmail());
        }

        return "redirect:/verify-code";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String InputEmail(@ModelAttribute User user, HttpSession session) {
        session.setAttribute("email", user.getEmail());
        accountService.sendVerificationEmail(user.getEmail());
        return "redirect:/code";
    }

    @GetMapping("/confirmpassword")
    public String confirm() {
        return "confirmpassword";
    }

    @PostMapping("/confirmpassword/new")
    public String confirmPassword(@ModelAttribute User user, RedirectAttributes ra,
            @RequestParam("confirmPassword") String confirmPassword, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (!user.getPassword().equals(confirmPassword)) {
            ra.addFlashAttribute("passwordMismatch", "Passwords do not match");
            return "redirect:/confirmpassword";
        }
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            // Handle the case when the user with the given email doesn't exist
            // (You may want to display an error message or redirect to an error page)
            return "redirect:/confirmpassword";
        }

        // Update the password of the fetched user
        existingUser.setPassword(user.getPassword());

        // Save the updated user back to the database
        userRepository.save(existingUser);

        return "redirect:/loginpage";
    }

    @GetMapping("/code")
    public String showCode() {
        return "code";
    }

    @PostMapping("/code")
    public String Code(@RequestParam("verifyCode") String verifyCode, RedirectAttributes ra,
            HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (accountService.verifyCodeIsValid(verifyCode)) {
            // Mã verify code hợp lệ, thực hiện xác thực tài khoản tại đây
            User user = userRepository.findByEmail(email);
            user.setStatus(true);
            userRepository.save(user);
            // Thông báo cho người dùng rằng tài khoản đã được xác thực thành công
            ra.addFlashAttribute("message", "Account has been successfully verified!");

        } else {
            // Mã verify code không hợp lệ hoặc đã hết hạn
            // Thông báo cho người dùng biết rằng mã không hợp lệ
            ra.addFlashAttribute("message",
                    "The authentication code is invalid or has expired. Please check again or request to resend the code.");
            return "redirect:/code";
        }

        return "redirect:/confirmpassword";
    }
    @GetMapping("/verify-code")
    public String showVerifyPage() {
    return "verify-code";
    }
    // Xử lý mã verify code khi người dùng gửi form
    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam("verifyCode") String verifyCode,
    RedirectAttributes ra,
    HttpSession session) {
    String email = (String) session.getAttribute("email");
    if (accountService.verifyCodeIsValid(verifyCode)) {
    // Mã verify code hợp lệ, thực hiện xác thực tài khoản tại đây
    User user = userRepository.findByEmail(email);
    user.setStatus(true);
    userRepository.save(user);
    // Thông báo cho người dùng rằng tài khoản đã được xác thực thành công
    ra.addFlashAttribute("message", "Account has been successfully verified!");

    } else {
    // Mã verify code không hợp lệ hoặc đã hết hạn
    // Thông báo cho người dùng biết rằng mã không hợp lệ
    ra.addFlashAttribute("message","The authentication code is invalid or has expired. Please check again or request to resend the code.");
    return "redirect:/vertify-code";
    }

    return "redirect:/loginpage";
    }

    @GetMapping("/resend")
    public String resendVerifyCode(RedirectAttributes ra, HttpSession session) {
        // Kiểm tra xem email có tồn tại trong hệ thống hay không
        String email = (String) session.getAttribute("email");
        if (userRepository.existsByEmail(email)) {
            // Gửi lại mã verify code
            accountService.resendVerificationEmail(email);
            ra.addFlashAttribute("message", "The verification code has been sent back successfully.");
        } else {
            ra.addFlashAttribute("message", "Email does not exist in the system.");
        }

        return "redirect:/verify-code";
    }
}
