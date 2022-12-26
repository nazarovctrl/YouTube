package com.example.youtube.controller;

import com.example.youtube.config.security.CustomUserDetails;
import com.example.youtube.dto.channel.ChannelCreateDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.comment.CommentCreateDTO;
import com.example.youtube.dto.comment.CommentResponseDTO;
import com.example.youtube.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/comment")
@Tag(name = "Comment Controller", description = "This api used to control comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Channel create method", description = "User use this method to create channel")
    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> create(@Valid @RequestBody CommentCreateDTO dto) {
        log.info("Comment creating : comment = {}, video = {}", dto.getContent(), dto.getVideoId());
        CommentResponseDTO result = commentService.create(dto, getUserId());
        return ResponseEntity.ok(result);
    }

    private Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
