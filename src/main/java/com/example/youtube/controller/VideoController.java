package com.example.youtube.controller;

import com.example.youtube.dto.VideoShortInfo;
import com.example.youtube.dto.video.VideoCreateDTO;
import com.example.youtube.dto.video.VideoFullInfo;
import com.example.youtube.enums.Language;
import com.example.youtube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
@Tag(name = "Video Controller", description = "This controller for video")
public class VideoController {
    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }


    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Method for create ", description = "This method used to create a video")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VideoCreateDTO dto,
                                    @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        VideoShortInfo result = service.create(dto, language);
        return ResponseEntity.ok(result);
    }


}
