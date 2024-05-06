package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.profile.ProfileResult;
import com.techtalk.techtalkapi.application.profilephoto.ProfilePhotoRequest;
import com.techtalk.techtalkapi.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{username}")
    public ProfileResult profile(@PathVariable String username) {
        return profileService.profile(username);
    }

    @PutMapping("/profile-photo")
    public boolean profilePhoto(@RequestParam ProfilePhotoRequest request) {
        return profileService.profilePhotoChange(request);
    }
}
