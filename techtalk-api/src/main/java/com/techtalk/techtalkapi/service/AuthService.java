package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.login.LoginRequest;
import com.techtalk.techtalkapi.application.login.LoginResult;
import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import com.techtalk.techtalkapi.assembler.LoginResultAssembler;
import com.techtalk.techtalkapi.assembler.RegisterResultAssembler;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.user.User;
import com.techtalk.techtalkapi.domain.user.UserAssembler;
import com.techtalk.techtalkapi.validate.AuthValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final AuthValidator authValidator;
    private final RegisterResultAssembler registerResultAssembler;
    private final UserAssembler userAssembler;
    private final LoginResultAssembler loginResultAssembler;

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
            User user = userAssembler.applyUserWithRegisterRequest(request, encodedPassword);
            usersRepository.save(user);

            log.info("Register successfull with Username: {}", request.getUsername());
            return registerResultAssembler.applySuccessResult();
        } catch (Exception ex) {
            log.error("Unexpected error got register with username: {}", request.getUsername());
            throw new RuntimeException(); //TODO
        }
    }

    public LoginResult login(LoginRequest request) {
        log.info("Login started with username: {}", request.getUsername());

        try {
            Optional<User> userOptional = usersRepository.findByUsername(request.getUsername());
            if (userOptional.isEmpty()) {
                return loginResultAssembler.applyUserNotFoundResult("auth.user.exist");
            }

            User user = userOptional.get();
            if (!checkPassword(request.getPassword(), user.getPassword())) {
                return loginResultAssembler.applyFailureResult("auth.login.params.invalid");
            }

            if (!user.isActive()) {
                return loginResultAssembler.applyFailureResult("auth.user.inactive");
            }

            String jwtToken = generateJwtToken(user.getUsername());

            return loginResultAssembler.applySuccessResult(jwtToken);
        } catch (Exception ex) {
            log.error("Unexpected error got login with username: {}", request.getUsername());
            throw new RuntimeException(); //TODO
        }
    }

    private static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    private String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }

}
