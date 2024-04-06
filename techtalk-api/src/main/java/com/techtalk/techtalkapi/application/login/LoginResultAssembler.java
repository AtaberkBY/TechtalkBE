package com.techtalk.techtalkapi.application.login;

import com.techtalk.techtalkapi.configuration.MessageConfig;
import org.springframework.stereotype.Component;

@Component
public class LoginResultAssembler {

    public LoginResult applyUserNotFoundResult() {
        String message = MessageConfig.get("auth.user.doesNotExist");
        String[] messageCredentials = message.split(";", 2);

        return new LoginResult(
                false,
                messageCredentials[1],
                null
        );
    }

    public LoginResult applyFailureResult(String message) {
        return new LoginResult(
                false,
                MessageConfig.get(message),
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
