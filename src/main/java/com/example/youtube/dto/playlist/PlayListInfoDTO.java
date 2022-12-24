package com.example.youtube.dto.playlist;

import com.example.youtube.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListInfoDTO {
    private Integer id;
    private String channelId;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
}
