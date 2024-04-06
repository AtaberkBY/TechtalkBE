package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.forgotpassword.ForgotPasswordRequest;
import com.techtalk.techtalkapi.application.forgotpassword.ForgotPasswordResult;
import com.techtalk.techtalkapi.application.login.LoginRequest;
import com.techtalk.techtalkapi.application.login.LoginResult;
import com.techtalk.techtalkapi.application.login.LoginResultAssembler;
import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import com.techtalk.techtalkapi.application.register.RegisterResultAssembler;
import com.techtalk.techtalkapi.application.resetpassword.ResetPasswordRequest;
import com.techtalk.techtalkapi.application.resetpassword.ResetPasswordResult;
import com.techtalk.techtalkapi.data.ForgotPasswordRepository;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.forgotpassword.ForgotPassword;
import com.techtalk.techtalkapi.domain.forgotpassword.ForgotPasswordAssembler;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final AuthValidator authValidator;
    private final RegisterResultAssembler registerResultAssembler;
    private final UserAssembler userAssembler;
    private final LoginResultAssembler loginResultAssembler;
    private final EmailService emailService;
    private final ForgotPasswordAssembler forgotPasswordAssembler;

    public RegisterResult register(RegisterRequest request) {
        log.info("Register started with Username: {}", request.getUsername());

        try {
            if (usersRepository.existsByUsername(request.getUsername()) || usersRepository.existsByEmail(request.getEmail())) {
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
            throw new RuntimeException(ex);
        }
    }

    public LoginResult login(LoginRequest request) {
        log.info("Login started with username: {}", request.getUsername());

        try {
            Optional<User> userOptional = usersRepository.findByUsername(request.getUsername());
            if (userOptional.isEmpty()) {
                return loginResultAssembler.applyUserNotFoundResult();
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
            throw new RuntimeException(ex);
        }
    }

    public ForgotPasswordResult forgotPassword(ForgotPasswordRequest request){
        log.info("Forgot Password started with email: {}", request.getEmail());
        try {
            Optional<User> userOptional = usersRepository.findByEmail(request.getEmail());
            if (userOptional.isEmpty()) {
                return new ForgotPasswordResult(false,"auth.user.doesNotExist");
            }

            User user = userOptional.get();
            String forgotToken = UUID.randomUUID().toString();

            ForgotPassword forgotPassword = forgotPasswordAssembler.applyForgotPassword(forgotToken, user.getEmail());
            forgotPasswordRepository.save(forgotPassword);

            String emailResult = emailService.sendSimpleMail(user.getEmail(),forgotToken);
            if(emailResult.isEmpty()){
                return new ForgotPasswordResult(false,"Sistem hatası...");
            }
            return new ForgotPasswordResult(true,emailResult);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ResetPasswordResult resetPassword(ResetPasswordRequest request, String token){
        try{
            Optional<ForgotPassword> forgotPasswordOptional = forgotPasswordRepository.findByToken(token);

            if(forgotPasswordOptional.isEmpty()){
                return new ResetPasswordResult(false, "Kayıtlı token bulunamadı.");
            }

            ForgotPassword forgotPassword = forgotPasswordOptional.get();

            if(forgotPassword.getCreatedDate().plusDays(1).isBefore(LocalDateTime.now())){
                if(forgotPassword.isActive()){
                    deactivateToken(forgotPassword);
                }
                return new ResetPasswordResult(false,"Şifre sıfırlama linkinin süresi dolmuştur.");
            }

            if(!forgotPassword.isActive()){
                return new ResetPasswordResult(false,"Şifre sıfırlama linki aktif değildir.");
            }

            Optional<User> userOptional = usersRepository.findByEmail(forgotPassword.getEmail());

            if (userOptional.isEmpty()){
                return new ResetPasswordResult(false,"Kayıtlı kullanıcı bulunamadı.");
            }

            User user = userOptional.get();

            if(request.getPassword().equals(request.getConfirmPassword())){
                String encodedPassword = encodePassword(request.getPassword());
                user.setPassword(encodedPassword);
                deactivateToken(forgotPassword);
                usersRepository.save(user);
                return new ResetPasswordResult(true, "Şifreniz Başarıyla Değişti.");
            }
            return new ResetPasswordResult(false, "Şifre ve Şifre onayla alanları aynı olmalıdır.");
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private void deactivateToken(ForgotPassword forgotPassword){
        forgotPassword.setActive(false);
        forgotPasswordRepository.save(forgotPassword);
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
