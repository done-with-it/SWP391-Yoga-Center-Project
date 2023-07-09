// package com.fptyoga.yogacenter.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.fptyoga.yogacenter.service.EmailService;

// @Controller
// @RequestMapping("/contact")
// public class ContactController {
//     private final EmailService emailService;

//     @Autowired
//     public ContactController(EmailService emailService) {
//         this.emailService = emailService;
//     }

//     @PostMapping("/send")
//     public String sendMessage(
//             @RequestParam("firstName") String firstName,
//             @RequestParam("lastName") String lastName,
//             @RequestParam("email") String email,
//             @RequestParam("phoneNumber") String phoneNumber,
//             @RequestParam("message") String message) {

//         // Xử lý logic và tạo nội dung email
//         String emailContent = "Name: " + firstName + " " + lastName + "\n" +
//                 "Email: " + email + "\n" +
//                 "Phone Number: " + phoneNumber + "\n" +
//                 "Message: " + message;

//         // Gửi email
//         emailService.sendEmail("luongpdse162038@fpt.edu.vn", "New message from website", emailContent);

//         // Chuyển hướng về trang thành công (hoặc trang cảm ơn)
//         return "success";
//     }
// }


