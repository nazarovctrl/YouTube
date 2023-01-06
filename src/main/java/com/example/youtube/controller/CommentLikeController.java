package com.example.youtube.controller;

import com.example.youtube.config.security.CustomUserDetails;
import com.example.youtube.dto.comment.like.CommentLikeCreateDTO;
import com.example.youtube.dto.comment.like.CommentLikeInfo;
import com.example.youtube.enums.Language;
import com.example.youtube.service.CommentLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment_like")
@Slf4j
@Tag(name = "Comment Like Controller", description = "This controller to like comment")
public class CommentLikeController {
    private final CommentLikeService service;

    public CommentLikeController(CommentLikeService service) {
        this.service = service;
    }


    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Method for create comment like ", description = "This method is used to like a comment")
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@Valid @RequestBody CommentLikeCreateDTO dto,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        log.info("{} is creating a like", getUserId());
        Boolean result = service.create(dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Method for delete comment like", description = " This method is used to delete comment like ")
    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity<Boolean> delete(@PathVariable("comment_id") Integer commentId,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        log.info("Like is deleting: {}", commentId);
        Boolean result = service.remove(commentId, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Method for get liked comments", description = "This method is used to get all liked comment")
    @GetMapping("/list")
    public ResponseEntity<List<CommentLikeInfo>> getLikedVideos() {
        List<CommentLikeInfo> result = service.getListByUserId(getUserId());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for get liked comment", description = "This method is used to get all liked comment")
    @GetMapping("/list/{id}")
    public ResponseEntity<List<CommentLikeInfo>> getUserLiked(@PathVariable Integer id) {
        System.out.println("Method is working");
        List<CommentLikeInfo> result = service.getListByUserId(id);
        return ResponseEntity.ok(result);
    }

    private Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }

}
