package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.register.RegisterHandler;
import com.techtalk.techtalkapi.application.register.RegisterRequest;
import com.techtalk.techtalkapi.application.register.RegisterResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final RegisterHandler registerHandler;

    @PostMapping("/register")
    public @ResponseBody RegisterResult register(@RequestBody RegisterRequest registerRequest){
        return registerHandler.handle(registerRequest);
    }

}
