package com.example.youtube.dto.playlistVideo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoDeleteDTO {
    @NotNull(message = "Playlist Id Required")
    private Integer playlistId;

    @NotNull(message = "Video Id Required")
    private Integer videoId;

    @Min(1)
    private Integer orderNum;
}
