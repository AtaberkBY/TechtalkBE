package com.techtalk.techtalkapi.application.profilephoto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfilePhotoRequest {
    private String username;
    private byte[] profilePhoto;
}
