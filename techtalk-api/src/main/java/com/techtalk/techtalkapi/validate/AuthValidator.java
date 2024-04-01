package com.techtalk.techtalkapi.validate;

import com.techtalk.techtalkapi.application.register.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {
    public String validateRegister(RegisterRequest request) {
        if (request.getUsername().length() < 4) {
            return "auth.username-length.invalid";
        }
        return null;
    }
}
