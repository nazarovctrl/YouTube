package com.example.youtube.dto.comment.like;

import com.example.youtube.enums.LikeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeCreateDTO {

    @NotBlank(message = "Comment id required")
    private Integer commentId;

    @NotNull(message = "Comment like type  required")
    private LikeType likeType;
}
