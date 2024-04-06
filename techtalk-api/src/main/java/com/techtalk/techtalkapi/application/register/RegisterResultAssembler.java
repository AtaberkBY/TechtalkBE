package com.techtalk.techtalkapi.application.register;

import org.springframework.stereotype.Component;

@Component
public class RegisterResultAssembler {
    public RegisterResult applyNotValidResult(String message) {

        return new RegisterResult(
                false,
                message,
                null
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
