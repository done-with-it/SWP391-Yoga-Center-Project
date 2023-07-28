package com.fptyoga.yogacenter.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fptyoga.yogacenter.Entity.Course;
import com.fptyoga.yogacenter.repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findByStatus(true);
    }

    public Course getCourseById(Long courseid) {
        Optional<Course> userOptional = courseRepository.findById(courseid);
        return userOptional.orElse(null);
    }

    public void saveCourse(MultipartFile file, Course course) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            course.setImg(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content

            // Lưu đối tượng Content vào cơ sở dữ liệu
            courseRepository.save(course);
        }
    }

    public byte[] getPngDataById(Long courseid) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        Course course = courseRepository.findById(courseid).orElse(null);

        if (course != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (course.getImg() != null) {
                return course.getImg();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for course: " + courseid);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("User not found for course: " + courseid);
        }
    }
    public int totalCourse(){
        return courseRepository.CountCourseWithStatusTrue();
    }
    public boolean CheckCoursename(String coursename){
            return courseRepository.existsByCoursename(coursename);
        }
}
