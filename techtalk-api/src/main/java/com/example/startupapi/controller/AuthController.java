package com.example.startupapi.controller;

import com.example.startupapi.application.login.LoginCommand;
import com.example.startupapi.application.login.LoginResult;
import com.example.startupapi.application.register.RegisterCommand;
import com.example.startupapi.application.register.RegisterResult;
import com.example.startupapi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public @ResponseBody RegisterResult register(@RequestBody RegisterCommand registerCommand){
        return authService.register(registerCommand);
    }

    @PostMapping("/login")
    public @ResponseBody LoginResult login(@RequestBody LoginCommand loginCommand){
        return authService.login(loginCommand);
    }
}
