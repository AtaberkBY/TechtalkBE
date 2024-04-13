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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "user_id")
    private Long userId;
    private String message;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "dislike_count")
    private int dislikeCount;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
