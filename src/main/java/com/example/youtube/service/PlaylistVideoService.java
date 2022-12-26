package com.example.youtube.service;

import com.example.youtube.dto.playlist.PlaylistResponseDTO;
import com.example.youtube.dto.playlistVideo.PlaylistVideoCreateDTO;
import com.example.youtube.dto.playlistVideo.PlaylistVideoUpdateDTO;
import com.example.youtube.entity.PlaylistVideoEntity;
import com.example.youtube.entity.VideoEntity;
import com.example.youtube.enums.Language;
import com.example.youtube.exp.VideoNotFoundException;
import com.example.youtube.repository.PlaylistVideoRepository;
import com.example.youtube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PlaylistVideoService {
    @Autowired
    private PlaylistVideoRepository playlistVideoRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public PlaylistResponseDTO create(PlaylistVideoCreateDTO dto, Integer userId, Language language) {
        Optional<VideoEntity> videoExists = videoRepository.findById(dto.getVideoId());
        if (videoExists.isEmpty()) {
            throw new VideoNotFoundException(resourceBundleService.getMessage("video.not.found", language));
        }
        PlaylistVideoEntity entity = new PlaylistVideoEntity();
        entity.setPlaylistId(dto.getPlaylistId());
        entity.setVideoId(dto.getVideoId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setOrderNum(dto.getOrderNum());
        playlistVideoRepository.save(entity);

        PlaylistResponseDTO response = new PlaylistResponseDTO();
        response.setId(entity.getId());

        return null;

    }

    public Boolean update(PlaylistVideoUpdateDTO dto, Integer userId, Language language) {
        Optional<PlaylistVideoEntity> byVideoIdAndPlaylistId = playlistVideoRepository.findByVideoIdAndPlaylistId(dto.getVideoId(), dto.getPlaylistId());
        byVideoIdAndPlaylistId.orElseThrow(() -> {
            throw new RuntimeException();
        });

        PlaylistVideoEntity entity = byVideoIdAndPlaylistId.get();
        if (entity.getVideo().getOwnerId() == userId) {
            throw new RuntimeException();
        }

        entity.setOrderNum(dto.getOrderNum());
        playlistVideoRepository.save(entity);
        return true;
    }


}
