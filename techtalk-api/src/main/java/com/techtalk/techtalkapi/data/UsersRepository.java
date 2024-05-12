package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.isActive = true ORDER BY u.point DESC")
    List<User> getUsersOrderByPoint(boolean isActive);
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE %:key%")
    List<User> searchUsersByUsername(String key);
}
