package com.example.youtube.repository;

import com.example.youtube.entity.CommentLikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity, Integer> {

    CommentLikeEntity findByProfileIdAndCommentId(Integer profileId, Integer commentId);

    List<CommentLikeEntity> findByProfileId(Integer userId);
}