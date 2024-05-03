package com.techtalk.techtalkapi.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String message;
    private String topic;
    private String tag;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "dislike_count")
    private int dislikeCount;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    private boolean isActive;
    private int commentCount;
    private String userProfilePhotoUrl;
}
