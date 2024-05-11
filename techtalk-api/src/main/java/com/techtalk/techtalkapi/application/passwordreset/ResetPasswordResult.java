package com.techtalk.techtalkapi.application.passwordreset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResetPasswordResult {
    private boolean success;
    private String resultMessage;
}
