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
        String title = user.getTitle();

        if(title == null) {
        user.setTitle("Unemployed");
        }

        if (currentPoints >= 240) {
            title = "Senior";
            level = 3;
        } else if (currentPoints >= 120) {
            level = 2;
            title = "Junior";
        } else if (currentPoints >= 50) {
            level = 1;
            title = "Intern";
        }

        if (user.getLevel() != level) {
            user.setTitle(title);
            user.setLevel(level);
        }
    }
}
