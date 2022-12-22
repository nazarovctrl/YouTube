package com.example.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@Table(name = "attach")
public class AttachEntity {

    @Id
    @GeneratedValue(generator = "attach_uuid")
    @GenericGenerator(name = "attach_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "orogin_name")
    private String originName;

    @Column
    private Long size;

    @Column
    private String type;

    @Column
    private String path;

    @Column
    private Double duration;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
