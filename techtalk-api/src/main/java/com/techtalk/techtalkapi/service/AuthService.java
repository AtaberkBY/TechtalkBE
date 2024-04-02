package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import com.techtalk.techtalkapi.assembler.RegisterResultAssembler;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.user.User;
import com.techtalk.techtalkapi.domain.user.UserAssembler;
import com.techtalk.techtalkapi.validate.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final AuthValidator authValidator;
    private final RegisterResultAssembler registerResultAssembler;
    private final UserAssembler userAssembler;

    public RegisterResult register(RegisterRequest request) {
        log.info("Register started with Username: {}", request.getUsername());

        try {
            if (usersRepository.existsByUsername(request.getUsername())) {
                return registerResultAssembler.applyUserExistResult();
            }

            String validateMessage = authValidator.validateRegister(request);
            if (Objects.nonNull(validateMessage)) {
                return registerResultAssembler.applyNotValidResult(validateMessage);
            }

            String encodedPassword = encodePassword(request.getPassword());
            User user = userAssembler.applyUser(request, encodedPassword);
            usersRepository.save(user);

            log.info("Register successfull with Username: {}", request.getUsername());
            return registerResultAssembler.applySuccessResult();
        } catch (Exception ex) {
           throw new RuntimeException(); //TODO
        }
    }

    private static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
