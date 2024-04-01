package com.techtalk.techtalkapi.assembler;

import com.techtalk.techtalkapi.application.register.RegisterResult;
import org.springframework.stereotype.Component;

@Component
public class RegisterResultAssembler {
    public RegisterResult applyNotValidResult(String message) {
        String[] messageCredentials = message.split(";", 2);

        return new RegisterResult(
                false,
                messageCredentials[1],
                messageCredentials[0]
        );
    }
}
