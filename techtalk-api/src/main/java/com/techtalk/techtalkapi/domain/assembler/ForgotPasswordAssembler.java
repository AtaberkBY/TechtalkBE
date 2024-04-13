package com.techtalk.techtalkapi.domain.assembler;

import com.techtalk.techtalkapi.domain.model.ForgotPassword;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ForgotPasswordAssembler {
    public ForgotPassword applyForgotPassword(String token, String email){
        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setEmail(email);
        forgotPassword.setToken(token);
        forgotPassword.setCreatedDate(LocalDateTime.now());
        forgotPassword.setActive(true);

        return forgotPassword;
    }
}
