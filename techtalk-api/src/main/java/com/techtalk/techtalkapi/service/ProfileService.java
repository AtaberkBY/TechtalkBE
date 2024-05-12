package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.profile.ProfileResult;
import com.techtalk.techtalkapi.application.profilephoto.ProfilePhotoRequest;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public boolean profilePhotoChange(ProfilePhotoRequest request) {
        log.info("Profile Photo Change started with username: {}", request.getUsername());
        try {
            User user = usersRepository.findByUsername(request.getUsername()).orElse(null);
            if (Objects.isNull(user)) {
                log.warn("Profile Photo Change failed, no user found with username: {}", request.getUsername());
                return false;
            }
            user.setProfilePhotoUrl(request.getUrl());
            usersRepository.save(user);

            log.info("Profile Photo Change successful with username: {}", request.getUsername());
            return true;
        } catch (Exception ex) {
            log.error("Profile Photo Change error with username: {}, error: {}", request.getUsername(), ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public List<User> leaderboard() {
        log.info("Leaderboard started");
        try {
            return usersRepository.getUsersOrderByPoint(true);
        } catch (Exception ex) {
            log.error("Leaderboard error: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
