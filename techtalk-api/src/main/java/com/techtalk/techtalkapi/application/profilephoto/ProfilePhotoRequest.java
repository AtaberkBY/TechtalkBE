package com.techtalk.techtalkapi.application.profilephoto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class ProfilePhotoRequest {
    private String username;
    private MultipartFile profilePhoto;
}
