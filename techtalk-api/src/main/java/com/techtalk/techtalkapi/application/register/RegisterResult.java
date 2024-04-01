package com.techtalk.techtalkapi.application.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResult {
    private boolean success;
    private String resultMessage;
    private String resultCode;
}
