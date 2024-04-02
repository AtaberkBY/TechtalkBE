package com.techtalk.techtalkapi.domain.user;

import com.techtalk.techtalkapi.application.register.RegisterRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserAssembler {

    public User applyUser(RegisterRequest request, String encodedPassword){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setActive(true);
        user.setCreatedDate(LocalDateTime.now());
        user.setPoint(0);
        user.setCommentCount(0);
        user.setTitle("user");

        return user;
    }
}
