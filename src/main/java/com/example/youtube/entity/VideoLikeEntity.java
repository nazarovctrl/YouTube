package com.example.youtube.entity;

import com.example.youtube.enums.LikeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "video_like", uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "video_id"}))
public class VideoLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;

    @ManyToOne
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column
    private LikeType likeType;
}
