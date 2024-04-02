package com.techtalk.techtalkapi.application.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResult {
    private boolean success;
    private String resultMessage;
    private String jwtToken;
}
