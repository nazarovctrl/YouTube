package com.example.youtube.controller;

import com.example.youtube.config.security.CustomUserDetails;
import com.example.youtube.dto.playlist.PlaylistCreateDTO;
import com.example.youtube.dto.playlist.PlaylistResponseDTO;
import com.example.youtube.dto.playlistVideo.PlaylistVideoCreateDTO;
import com.example.youtube.dto.playlistVideo.PlaylistVideoUpdateDTO;
import com.example.youtube.enums.Language;
import com.example.youtube.service.PlaylistService;
import com.example.youtube.service.PlaylistVideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/playlist/video")
@Tag(name = "Playlist Video Controller", description = "This api used to control videos in playlist")
public class PlaylistVideoController {
    @Autowired
    private PlaylistVideoService playlistVideoService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Playlist create video method", description = "User use this method create  video to playlist")
    @PostMapping("/create")
    public ResponseEntity<PlaylistResponseDTO> create(@Valid @RequestBody PlaylistVideoCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        log.info("Creating video to playlist: playlist = {}, video = {}", dto.getPlaylistId(), dto.getVideoId());
        PlaylistResponseDTO result = playlistVideoService.create(dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Playlist video update method", description = "User use this method to create playlist")
    @PutMapping("/update")
    public ResponseEntity<Boolean> create(@Valid @RequestBody PlaylistVideoUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        log.info("Creating video to playlist: playlist = {}, video = {}", dto.getPlaylistId(), dto.getVideoId());
        Boolean result = playlistVideoService.update(dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }


    private Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
