package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.login.LoginRequest;
import com.techtalk.techtalkapi.application.login.LoginResult;
import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import com.techtalk.techtalkapi.application.forgotpassword.ForgotPasswordRequest;
import com.techtalk.techtalkapi.application.forgotpassword.ForgotPasswordResult;
import com.techtalk.techtalkapi.application.resetpassword.ResetPasswordRequest;
import com.techtalk.techtalkapi.application.resetpassword.ResetPasswordResult;
import com.techtalk.techtalkapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public @ResponseBody RegisterResult register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public @ResponseBody LoginResult login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/forgotpassword")
    public @ResponseBody ForgotPasswordResult forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return authService.forgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/resetpassword/{token}")
    public @ResponseBody ResetPasswordResult resetPassword(@RequestBody ResetPasswordRequest request, @PathVariable String token) {
        return authService.resetPassword(request,token);
    }

}
