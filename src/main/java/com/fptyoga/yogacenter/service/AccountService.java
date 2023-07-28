package com.fptyoga.yogacenter.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class AccountService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, LocalDateTime> verifyCodeMap = new HashMap<>();

    // Hàm tạo verify code ngẫu nhiên và thời gian hết hạn 30 phút
    private String generateVerifyCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6; // Độ dài của verify code
        StringBuilder verifyCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            verifyCode.append(characters.charAt(random.nextInt(characters.length())));
        }

        return verifyCode.toString();
    }

    // Hàm gửi email chứa verify code tới địa chỉ email của người dùng
    public void sendVerificationEmail(String userEmail) {
        String verifyCode = generateVerifyCode();

        // Lưu verifyCode và thời gian hết hạn vào map
        verifyCodeMap.put(verifyCode, LocalDateTime.now().plusMinutes(30));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);
            helper.setSubject("Account Verification");
            helper.setText("Your authentication code is: " + verifyCode);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Hàm gửi email chứa bill tới địa chỉ email của người dùng
    public void sendBill(String email, String orderInfo, long price, String Class, String course,
            String expired, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
            String formattedPrice = numberFormat.format(price);
            helper.setTo(email);
            helper.setSubject("FPT YOGA BILL");
            String emailContent = "<img src='cid:logo' alt='11zon Logo' width='100%' style='display: block; margin: 0 auto;'>"
                    + "<p style='font-size: 14px;'>Dear " + name + ",</p>"
                    + "<p style='font-size: 14px;'>Welcome to FPT Yoga Center. Here's your class information:</p>"
                    + "<p style='font-size: 14px;'>" + orderInfo + "<p>"
                    + "<p style='font-size: 14px;'>Course: " + course + "<p>"
                    + "<p style='font-size: 14px;'>Class:" + Class + "<p>"
                    + "<p style='font-size: 14px;'>Price:" + formattedPrice + "<p>"
                    + "<p style='font-size: 14px;'>Expire on:" + expired + "<p>"
                    + "<p style='font-size: 14px;'>You can visit our website to see detailed information about your class schedule. Thank you for using our service!</p>"
                    + "<p style='font-size: 14px;'>Best regards," + "<p>"
                    + "<p style='font-size: 14px;'>FPT Yoga" + "<p>";

            ClassPathResource logoResource = new ClassPathResource(
                    "/img/login/11zon_resized/logoV2-removebg-preview_1_11zon.png");
            helper.addInline("logo", logoResource);
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Hàm gửi lại verify code
    public void resendVerificationEmail(String userEmail) {
        // Tạo verify code mới
        String newVerifyCode = generateVerifyCode();

        // Lưu verify code mới và thời gian hết hạn mới vào map
        verifyCodeMap.put(newVerifyCode, LocalDateTime.now().plusMinutes(30));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);
            helper.setSubject("Xác thực tài khoản - Gửi lại");
            helper.setText("Mã xác thực mới của bạn là: " + newVerifyCode);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra mã verify code có hiệu lực hay không
    public boolean verifyCodeIsValid(String verifyCode) {
        LocalDateTime expirationTime = verifyCodeMap.get(verifyCode);
        if (expirationTime == null || expirationTime.isBefore(LocalDateTime.now())) {
            // Mã không hợp lệ hoặc đã hết hạn
            return false;
        }

        // Mã hợp lệ
        return true;
    }

}
