package com.fptyoga.yogacenter.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fptyoga.yogacenter.Entity.Content;
import com.fptyoga.yogacenter.repository.ContentRepository;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repo;

    public List<Content> listAllContent() {
        return repo.findAll();
    }

    public List<String> getFilterContent() {
        return repo.findDistinctTopics();
    }

    public Page<Content> getContent(String topic, Pageable page) {
        return repo.findByTopicAndStatusOrderByCreatedateDesc(topic, true, page);
    }

    public Content getContentsBlog(Long contentid) {
        Optional<Content> contents = repo.findById(contentid);
        return contents.orElse(null);
    }

    public void saveContent(MultipartFile file, Content content) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            content.setImg(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content
            content.setCreatedate(LocalDate.now());

            // Lưu đối tượng Content vào cơ sở dữ liệu
            repo.save(content);
        }
    }

    public byte[] getPngDataById(Long contentId) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        Content content = repo.findById(contentId).orElse(null);

        if (content != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (content.getImg() != null) {
                return content.getImg();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for contentId: " + contentId);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("Content not found for contentId: " + contentId);
        }
    }

    public Page<Content> getAllContentsByStatus(Pageable page) {
        return repo.findAllByStatusOrderByCreatedateDesc(true, page);
    }
}
