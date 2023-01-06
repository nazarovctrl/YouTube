package com.example.youtube.dto.comment.like;


import com.example.youtube.enums.LikeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeInfo {

    private Integer id;
    private Integer profileId;
    private Integer commentId;
    private LocalDateTime createdDate;
    private LikeType type;
}
