package com.fptyoga.yogacenter.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fptyoga.yogacenter.Entity.User;
import com.fptyoga.yogacenter.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll(Long role) {
        return repo.findByRole_Roleid(role);

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
            throw new RuntimeException("Content not found for contentId: " + userid);
        }
    }

}