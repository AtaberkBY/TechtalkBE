package com.techtalk.techtalkapi.application.register;

import com.techtalk.techtalkapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterHandler {

    private final AuthService authService;

    public RegisterResult handle(RegisterRequest request) {
        log.info("Register handle started");
        return authService.register(request);
    }
}
