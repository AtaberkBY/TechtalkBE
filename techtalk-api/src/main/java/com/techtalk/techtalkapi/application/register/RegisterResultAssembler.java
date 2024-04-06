package com.techtalk.techtalkapi.application.register;

import com.techtalk.techtalkapi.configuration.MessageConfig;
import org.springframework.stereotype.Component;

@Component
public class RegisterResultAssembler {
    public RegisterResult applyNotValidResult(String messageCode) {
        String[] messageCredentials = MessageConfig.get(messageCode).split(";", 2);

        return new RegisterResult(
                false,
                messageCredentials[1],
                messageCredentials[0]
        );
    }

    public RegisterResult applyUserExistResult() {
        String messageCode = "auth.user.exist";
        String[] messageCredentials = MessageConfig.get(messageCode).split(";", 2);

        return new RegisterResult(
                false,
                messageCredentials[1],
                messageCredentials[0]
        );
    }

    public RegisterResult applySuccessResult() {
        return new RegisterResult(
                true,
                null,
                "00"
        );
    }
}
