package com.techtalk.techtalkapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String title;
    private String profilePhotoUrl;
    private int commentCount;
    private BigDecimal point;
    private LocalDate createdDate;
    private boolean isActive;
    private boolean isBanned;
    private String banReason;
}
