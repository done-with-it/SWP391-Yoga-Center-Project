// package com.fptyoga.yogacenter.controller;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import com.fptyoga.yogacenter.Entity.Content;
// import com.fptyoga.yogacenter.repository.ContentRepository;

// @Controller
// public class FileController {

//     @Autowired
//     private ContentRepository contentRepository;

//     @GetMapping("/admin/upload")
//     public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
//         // Xử lý file được tải lên
//         if (!file.isEmpty()) {
//             try {
//                 byte[] data = file.getBytes();

//                 Content content = new Content();
//                 // Gán nội dung của file vào thuộc tính 'data'
//                 content.setData(data);

//                 // Lưu các thuộc tính khác của 'content'
//                 content.setTopic("Yoga");
//                 content.setTitle("Yoga PDF");
//                 // ...

//                 // Lưu 'content' vào cơ sở dữ liệu
//                 contentRepository.save(content);

//                 model.addAttribute("message", "Tải file thành công!");
//             } catch (IOException e) {
//                 model.addAttribute("message", "Lỗi khi tải file: " + e.getMessage());
//             }
//         } else {
//             model.addAttribute("message", "Không có file được tải lên.");
//         }
//         // Trả về trang Thymeleaf
//         return "/admin/upload";
//     }
// }
