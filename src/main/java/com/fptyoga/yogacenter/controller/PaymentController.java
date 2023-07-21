package com.fptyoga.yogacenter.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fptyoga.yogacenter.Entity.Booking;
import com.fptyoga.yogacenter.Entity.Class;
import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.config.Config;
import com.fptyoga.yogacenter.repository.BookingRepository;
import com.fptyoga.yogacenter.repository.ClassesRepository;
import com.fptyoga.yogacenter.repository.UserRepository;
import com.fptyoga.yogacenter.service.AccountService;
import com.fptyoga.yogacenter.service.BookingService;

import jakarta.servlet.http.HttpSession;

@Controller

public class PaymentController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    private AccountService accountService;

    /**
     * @param classID
     * @param userID
     * @param session
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/create_payment")
    public String payment(@RequestParam(value = "classID") Long classID, @RequestParam(value = "date") String date,
            @RequestParam(value = "timeid") Long timeid, @RequestParam(value = "userID") Long userID,
            @RequestParam(value = "price") float price,
            RedirectAttributes ra, @RequestParam(value = "duration") Long duration)
            throws UnsupportedEncodingException {

        if (bookingRepository.countDuplicateClassIdsWithStatusTrue(classID) > 25) {
            ra.addFlashAttribute("mess", "The class is full");
            return "redirect:/classes";
        } else {

            if (bookingRepository.existsByUserIdAndClassidDateAndTimeIdAndStatus(userID, date, timeid, classID)
                    || !bookingRepository.existsByUserIdDateAndTimeIdAndStatus(userID, date, timeid)) {
                String orderType = "billpayment";

                long amount = Math.round(price) * 100;

                String vnp_TxnRef = Config.getRandomNumber(8);
                String vnp_TmnCode = Config.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", Config.vnp_Version);
                vnp_Params.put("vnp_Command", Config.vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");
                vnp_Params.put("vnp_BankCode", "NCB");
                vnp_Params.put("vnp_Locale", "vn");

                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Order info:" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);

                vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
                vnp_Params.put("vnp_IpAddr", Config.vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        // Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        // Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

                Class classes = new Class();
                classes.setClassid(classID);

                Booking book = new Booking();

                User user = new User();
                user.setUserid(userID);

                if (bookingRepository.existsByUserIdAndClassidDateAndTimeIdAndStatus(userID, date, timeid, classID)) {
                    book = bookingRepository.findByUserIdAndClassidDateAndTimeIdAndStatus(userID, date, timeid,
                            classID);
                    session.setAttribute("expired", book.getExpired());
                    book.setStatus(false);
                    bookingRepository.save(book);
                } else {
                    session.removeAttribute("expired");
                }

                book.setClassid(classes);
                book.setCustomerid(user);
                session.setAttribute("book", book);
                session.setAttribute("duration", duration);

                return "redirect:" + paymentUrl;
            } else {
                ra.addFlashAttribute("mess",
                        "You had booked the same class time, please choose another class !");
                return "redirect:/classes";
            }

        }

    }

    // }

    @Transactional
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String processPayment(@RequestParam("vnp_Amount") String amount,
            @RequestParam("vnp_BankCode") String bankCode,
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_OrderInfo") String order, Model model) {

        Booking book = (Booking) session.getAttribute("book");

        Long duration = (Long) session.getAttribute("duration");

        LocalDateTime expired = (LocalDateTime) session.getAttribute("expired");

        long cost = Long.valueOf(amount) / 100;
        // Tạo một đối tượng Payment và gán giá trị từ URL
        if (responseCode.equals("00")) {
            book.setStatus(true);
            book.setBookingdate(LocalDateTime.now());
            book.setBankCode(bankCode);
            book.setBookingOrder(order);
            book.setResponseCode(responseCode);
            book.setAmount(cost);
            if (expired != null && LocalDateTime.now().isBefore(expired)) {
                expired = expired.plusMinutes(duration.longValue());
                book.setExpired(expired);
            } else {
                expired = LocalDateTime.now().plusMinutes(duration.longValue());
                book.setExpired(expired);

            }

            bookingRepository.save(book);
            Booking booked = bookingService.getCourse(book.getBookingid());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
            String expired_in = booked.getExpired().format(formatter);

            Class classes = classesRepository.findById(Long.valueOf(book.getClassid().getClassid())).orElse(null);
            String course = classes.getCourseid().getCoursename();
            String classname = classes.getClassname();

            User user = userRepository.findById(Long.valueOf(book.getCustomerid().getUserid())).orElse(null);
            String email = user.getEmail();
            String name = user.getFullname();

            model.addAttribute("classes", classes);
            model.addAttribute("order", booked);
            model.addAttribute("email", email);
            model.addAttribute("name", name);

            accountService.sendBill(email, order, cost, classname, course, expired_in, name);
        } else {

            book.setStatus(false);
            book.setBookingdate(LocalDateTime.now());
            book.setBankCode(bankCode);
            book.setBookingOrder(order);
            book.setResponseCode(responseCode);
            book.setAmount(cost / 100);
            bookingRepository.save(book);
            return "redirect:/index";

        }

        return "payment";
    }

}
