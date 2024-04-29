package com.techtalk.techtalkapi.utility;

import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointUtility {
    private final UsersRepository usersRepository;

    public void givePointToUser(String username, int point) {
        User user = usersRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setPoint(user.getPoint() + point);
            updateLevel(user);

            usersRepository.save(user);
        }
    }

    public void updateLevel(User user) {
        double currentPoints = user.getPoint();
        int level = user.getLevel();

        if (currentPoints >= 240) {
            level = 3;
        } else if (currentPoints >= 120) {
            level = 2;
        } else if (currentPoints >= 50) {
            level = 1;
        }

        if (user.getLevel() != level) {
            user.setLevel(level);
        }
    }
}
