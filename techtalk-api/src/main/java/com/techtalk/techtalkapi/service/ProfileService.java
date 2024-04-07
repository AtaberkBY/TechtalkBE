package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.profile.ProfileResult;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final UsersRepository usersRepository;

    public ProfileResult profile(String username) {
        log.info("Get Profile started with username: {}", username);
        try {
            Optional<User> userOptional = usersRepository.findByUsername(username);
            return userOptional.map(user -> new ProfileResult(true, user)).orElseGet(() -> new ProfileResult(false, null));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
