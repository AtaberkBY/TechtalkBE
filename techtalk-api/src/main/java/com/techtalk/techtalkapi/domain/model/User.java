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
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String title;
    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;
    @Column(name = "comment_count")
    private int commentCount;
    private double point;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_banned")
    private boolean isBanned;
}
