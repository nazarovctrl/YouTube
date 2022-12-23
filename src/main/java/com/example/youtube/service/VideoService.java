package com.example.youtube.service;

import com.example.youtube.dto.VideoShortInfo;
import com.example.youtube.dto.attach.PreviewAttachDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.channel.ChannelShortDTO;
import com.example.youtube.dto.video.VideoCreateDTO;
import com.example.youtube.entity.VideoEntity;
import com.example.youtube.enums.Language;
import com.example.youtube.enums.VideoStatus;
import com.example.youtube.repository.VideoRepository;
import org.springframework.stereotype.Service;


@Service
public class VideoService {
    private final VideoRepository repository;

    private final AttachService attachService;
    private final ChannelService channelService;

    public VideoService(VideoRepository repository, AttachService attachService, ChannelService channelService) {
        this.repository = repository;
        this.attachService = attachService;
        this.channelService = channelService;
    }


    public VideoShortInfo create(VideoCreateDTO dto, Language language) {
        VideoEntity entity = new VideoEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setPreviewAttachId(dto.getPreviewAttachId());

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        entity.setStatus(VideoStatus.PRIVATE);


        repository.save(entity);

        ChannelResponseDTO channel = channelService.getById(entity.getChannelId(), language);

        VideoShortInfo responseDTO = new VideoShortInfo();
        responseDTO.setId(entity.getId());
        responseDTO.setDuration(dto.getDuration());
        responseDTO.setViewCount(entity.getViewCount());
        responseDTO.setTitle(entity.getTitle());
        responseDTO.setPublishedDate(entity.getPublishedDate());

        ChannelShortDTO channelShortDTO = new ChannelShortDTO();
        channelShortDTO.setId(channel.getId());
        channelShortDTO.setName(channel.getName());
        channelShortDTO.setPhotoUrl(attachService.getUrl(channel.getPhotoId(), language));

        PreviewAttachDTO previewAttachDTO = new PreviewAttachDTO();
        previewAttachDTO.setId(entity.getPreviewAttachId());
        previewAttachDTO.setUrl(attachService.getUrl(entity.getAttachId(), language));




        responseDTO.setChannel(channelShortDTO);
        responseDTO.setPreviewAttach(previewAttachDTO);


        return responseDTO;

    }


}
