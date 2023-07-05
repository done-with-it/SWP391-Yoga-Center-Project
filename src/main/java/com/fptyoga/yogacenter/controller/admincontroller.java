package com.fptyoga.yogacenter.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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

import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.Entity.Role;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.ContentService;
import com.fptyoga.yogacenter.service.RoleService;
import com.fptyoga.yogacenter.service.UserService;

@Controller
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String trainer(Model model, @RequestParam(defaultValue = "") Long roleid) {
        if (roleid != null) {
            List<User> userList = userService.listAll(roleid);
            model.addAttribute("userList", userList);
        } else {
            List<User> userList = userRepository.findAll();
            model.addAttribute("userList", userList);
        }
        return "admin/index";
    }

    @GetMapping("/401")
    public String show401() {
        return "admin/401";
    }

    @GetMapping("/404")
    public String show404() {
        return "admin/404";
    }

    @GetMapping("/500")
    public String show500() {
        return "admin/500";
    }

    @Autowired
    private RoleService roleService;

    @GetMapping("/adduser")
    public String addUser(Model model) {
        List<Role> rolesList = roleService.allRole();
        model.addAttribute("rolesList", rolesList);
        model.addAttribute("user", new User());
        return "admin/adduser";
    }

    @PostMapping("/adduser/new")
    public String newUser(@ModelAttribute User user, RedirectAttributes ra, @RequestParam("file") MultipartFile file) {
        user.setRegistrationdate(LocalDate.now());
        user.setStatus(true);
        userRepository.save(user);
        try {
            userService.saveUser(file, user);
        } catch (IOException e) {
            // Xử lý lỗi nếu cần
        }
        ra.addFlashAttribute("message", "The user has been added successfully.");
        ra.addFlashAttribute("update", "The user has been added successfully.");
        return "redirect:/admin/adduser";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setStatus(false);
        userRepository.save(user);
        return "redirect:/admin/index";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        List<Role> rolesList = roleService.allRole();
        model.addAttribute("rolesList", rolesList);
        try {
            User user = userRepository.findById(id).orElse(null);
            model.addAttribute("user", user);
            return "admin/edit";
        } catch (Exception e) {
        }
        return "admin/edit";
    }

    @GetMapping("/download-png")
    public ResponseEntity<Resource> downloadTrainerPng(@RequestParam(defaultValue = "") Long userid) {
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

    @Autowired
    private ContentService contentService;

    @GetMapping("/upload")
    public String uploadFile(Model model) {
        model.addAttribute("content", new Content());
        return "admin/upload";
    }

    @PostMapping("/upload")
    public String uploadContent(@ModelAttribute("content") Content content,
            @RequestParam("file") MultipartFile file) {
        try {
            contentService.saveContent(file, content);
        } catch (IOException e) {
            // Xử lý lỗi nếu cần
        }
        return "redirect:/admin/upload";
    }

    @GetMapping("/image-content")
    public ResponseEntity<Resource> downloadContentPng(@RequestParam(defaultValue = "") Long contentId) {
        byte[] pngData = contentService.getPngDataById(contentId);
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

}
