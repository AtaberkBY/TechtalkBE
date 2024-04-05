package com.techtalk.techtalkapi.application.resetpassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResetPasswordRequest {
    private String password;
    private String confirmPassword;
}
