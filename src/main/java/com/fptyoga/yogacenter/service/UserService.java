package com.fptyoga.yogacenter.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.dto.MonthlyTotal;
import com.fptyoga.yogacenter.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll(Long role) {
        return repo.findByRole_RoleidAndStatus(role, true);

    }

    public User getUser(Long userid) {
        Optional<User> userOptional = repo.findById(userid);
        return userOptional.orElse(null);
    }

    public void saveUser(MultipartFile file, User user) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            user.setImg(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content

            // Lưu đối tượng Content vào cơ sở dữ liệu
            repo.save(user);
        }
    }

    public byte[] getPngDataById(Long userid) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        User user = repo.findById(userid).orElse(null);

        if (user != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (user.getImg() != null) {
                return user.getImg();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for User: " + userid);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("User not found for UsertId: " + userid);
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Integer> getUsersPerMonth() {
        String query = "SELECT CONCAT(MONTH(u.registrationdate), '-', YEAR(u.registrationdate)) AS month, " +
                "COUNT(u.userid) AS count " +
                "FROM User u " +
                "GROUP BY MONTH(u.registrationdate), YEAR(u.registrationdate)";

        List<Object[]> results = entityManager.createQuery(query, Object[].class).getResultList();

        Map<String, Integer> usersPerMonth = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-yyyy");

        for (Object[] result : results) {
            String month = (String) result[0];
            Integer count = ((Number) result[1]).intValue();
            LocalDate date = LocalDate.parse(month, formatter);
            String formattedMonth = date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));

            usersPerMonth.put(formattedMonth, count);
        }

        return usersPerMonth;
    }

    public User login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public List<User> getUserByStatus(){
        return repo.findByStatus(true);
    }

    public List<User> listAllUserFalse(Long role) {
        return repo.findByRole_RoleidAndStatus(role, false);

    }
    public List<User> getUserByStatusFalse(){
        return repo.findByStatus(false);
    }

    public List<MonthlyTotal> getMonthlyUser() {
        List<Object[]> results = repo.getMonthlyUser();
        List<MonthlyTotal> monthlyUser = new ArrayList<>();

        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Long totalAmount = (Long) result[1];
            monthlyUser.add(new MonthlyTotal(month, totalAmount));
        }

        return monthlyUser;
    }

}