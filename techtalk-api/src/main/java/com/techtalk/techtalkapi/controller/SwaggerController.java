package com.techtalk.techtalkapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping("/")
    public String home(){
        return "redirect:/swagger-ui/index.html";
    }
}
