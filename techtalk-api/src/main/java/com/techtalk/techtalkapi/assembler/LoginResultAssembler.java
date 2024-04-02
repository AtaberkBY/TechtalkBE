package com.techtalk.techtalkapi.assembler;

import com.techtalk.techtalkapi.application.login.LoginResult;
import org.springframework.stereotype.Component;

@Component
public class LoginResultAssembler {
    public LoginResult applyUserNotFoundResult(String message) {
        String[] messageCredentials = message.split(";", 2);

        return new LoginResult(
                false,
                messageCredentials[1],
                messageCredentials[0]
        );
    }

    public LoginResult applyFailureResult(String message) {
        return new LoginResult(
                false,
                message,
                null
        );
    }

    public LoginResult applySuccessResult(String jwtToken) {
        return new LoginResult(
                true,
                "",
                jwtToken
        );
    }
}
