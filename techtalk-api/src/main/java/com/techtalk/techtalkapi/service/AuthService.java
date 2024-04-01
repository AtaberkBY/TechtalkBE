package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import com.techtalk.techtalkapi.assembler.RegisterResultAssembler;
import com.techtalk.techtalkapi.data.UserRepository;
import com.techtalk.techtalkapi.validate.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    //private final UserRepository userRepository;
    private final AuthValidator authValidator;
    private final RegisterResultAssembler registerResultAssembler;

    public RegisterResult register(RegisterRequest request) {
        try {
            String validateMessage = authValidator.validateRegister(request);
            if (validateMessage != null) {
                return registerResultAssembler.applyNotValidResult(validateMessage);
            }

            
        } catch (Exception ex) {

        }
        return null;
    }
}
