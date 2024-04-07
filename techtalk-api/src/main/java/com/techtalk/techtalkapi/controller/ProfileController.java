package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.profile.ProfileResult;
import com.techtalk.techtalkapi.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{username}")
    public ProfileResult profile(@PathVariable String username) {
        return profileService.profile(username);
    }

}
