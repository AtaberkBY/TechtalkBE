package com.techtalk.techtalkapi.application.forgotpassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ForgotPasswordResult {
    private boolean success;
    private String resultMessage;
}
