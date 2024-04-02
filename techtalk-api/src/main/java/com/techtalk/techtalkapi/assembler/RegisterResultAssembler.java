package com.techtalk.techtalkapi.assembler;

import com.techtalk.techtalkapi.application.register.RegisterRequest;
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

    public RegisterResult applyUserExistResult() {
        return new RegisterResult(
                false,
                "auth.user.exist",
                ""
        );
    }

    public RegisterResult applySuccessResult() {
        return new RegisterResult(
                true,
                "",
                "00"
        );
    }
}
