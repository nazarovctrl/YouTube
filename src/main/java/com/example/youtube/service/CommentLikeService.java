package com.example.youtube.service;

import com.example.youtube.dto.comment.like.CommentLikeCreateDTO;
import com.example.youtube.dto.comment.like.CommentLikeInfo;
import com.example.youtube.entity.CommentLikeEntity;
import com.example.youtube.enums.Language;
import com.example.youtube.exp.comment.LikeNotFoundException;
import com.example.youtube.exp.videoLike.AlreadyLikedException;
import com.example.youtube.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentLikeService {
    private final CommentLikeRepository repository;
    private final ResourceBundleService resourceBundleService;

    public CommentLikeService(CommentLikeRepository repository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.resourceBundleService = resourceBundleService;
    }

    public Boolean create(CommentLikeCreateDTO dto, Integer userId, Language language) {

        CommentLikeEntity exists = repository.findByProfileIdAndCommentId(userId, dto.getCommentId());
        if (exists != null) {
            throw new AlreadyLikedException(resourceBundleService.getMessage("already.liked", language));
        }
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setProfileId(userId);
        entity.setType(dto.getLikeType());
        repository.save(entity);
        return true;
    }

    public Boolean remove(Integer commentId, Integer userId, Language language) {
        CommentLikeEntity entity = repository.findByProfileIdAndCommentId(userId, commentId);

        if (entity == null) {
            throw new LikeNotFoundException(resourceBundleService.getMessage("", language));
        }
        repository.delete(entity);
        return true;
    }

    public List<CommentLikeInfo> getListByUserId(Integer userId) {
        List<CommentLikeEntity> entityList = repository.findByProfileId(userId);

        List<CommentLikeInfo> dtoList = new ArrayList<>();

        entityList.forEach(entity -> dtoList.add(getDTO(entity)));

        return dtoList;
    }


    private CommentLikeInfo getDTO(CommentLikeEntity entity) {
        CommentLikeInfo dto = new CommentLikeInfo();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfileId());
        dto.setCommentId(entity.getCommentId());
        dto.setType(entity.getType());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
