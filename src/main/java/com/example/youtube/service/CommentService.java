package com.example.youtube.service;

import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.comment.CommentCreateDTO;
import com.example.youtube.dto.comment.CommentResponseDTO;
import com.example.youtube.entity.CommentEntity;
import com.example.youtube.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentResponseDTO create(CommentCreateDTO dto, Integer userId) {
        CommentEntity comment = new CommentEntity();
        comment.setVideoId(dto.getVideoId());
        comment.setProfileId(userId);
        comment.setContent(dto.getContent());
        commentRepository.save(comment);

        CommentResponseDTO response = new CommentResponseDTO();
        response.setId(comment.getId());
        response.setProfileId(comment.getProfileId());
        response.setVideoId(comment.getVideoId());
        response.setContent(comment.getContent());

        return response;
    }
}
