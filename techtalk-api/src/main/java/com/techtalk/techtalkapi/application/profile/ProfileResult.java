package com.techtalk.techtalkapi.application.profile;

import com.techtalk.techtalkapi.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileResult {
    private boolean success;
    private User user;
}
