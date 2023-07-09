// package com.fptyoga.yogacenter.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.MailException;
// import org.springframework.mail.MailSender;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.stereotype.Service;

// @Service
// public class EmailService {
//     private final MailSender mailSender;

//     @Autowired
//     public EmailService(MailSender mailSender) {
//         this.mailSender = mailSender;
//     }

//     public void sendEmail(String to, String subject, String text) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo(to);
//         message.setSubject(subject);
//         message.setText(text);

//         try {
//             mailSender.send(message);
//         } catch (MailException e) {
//             // Xử lý lỗi gửi email
//         }
//     }
// }



